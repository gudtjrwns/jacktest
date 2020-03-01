package com.example.jacktest.controller;

import java.util.List;

import com.example.jacktest.entities.Reply;
import com.example.jacktest.services.NoticeService;
import com.example.jacktest.services.ReplyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class RestReplyController{

    @Autowired
    private ReplyService replyService;

    @Autowired
    private NoticeService noticeService;


    // 댓글 정보
    @GetMapping("/findByReplyOne/replyId={replyId}")
    public Reply findByReplyOne(@PathVariable("replyId") Long replyId) {
        Reply replyOne = replyService.getReplyOne(replyId);
        return replyOne;
    }


    // 댓글 목록
    @RequestMapping(value="/findAllReplyListByDistIdEquals/distName={distName}&distId={distId}", method=RequestMethod.GET)
    public ResponseEntity<List<Reply>> findAllReplyListByDistIdEquals(@PathVariable("distName") String distName,
                                                                    @PathVariable("distId") Long distId) {
        ResponseEntity<List<Reply>> entity = null;

        try {
            List<Reply> replyList = replyService.listAllReplyByDistIdEquals(distName, distId);
            entity = new ResponseEntity<>(replyList, HttpStatus.OK);
            
        }catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return entity;
    }


    // 댓글 등록
    @RequestMapping(value="/replyAddExecute/distName={distName}&distId={distId}&contents={contents}&writer={writer}", method=RequestMethod.POST)
    public ResponseEntity<Object> replyAddExecute(@PathVariable("distName") String distName,
                                                    @PathVariable("distId") Long distId,
                                                    @PathVariable("contents") String contents,
                                                    @PathVariable("writer") String writer) {
        ResponseEntity<Object> entity = null;

        try {
            Reply saveReply = replyService.saveReply(distName, distId, writer, contents);

            if(distName.equals("notice")){
                noticeService.addReplyCount(distId);
            }

            entity = new ResponseEntity<Object>(saveReply, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
    

    // 댓글 수정
    @RequestMapping(value="/replyEditExecute/replyId={replyId}&contents={contents}", method = RequestMethod.PUT)
    public ResponseEntity<Object> replyEditExecute(@PathVariable("replyId") Long replyId,
                                                    @PathVariable("contents") String contents) {
        ResponseEntity<Object> entity = null;

        try{
            Reply saveReply = replyService.editReply(replyId, contents);
            entity = new ResponseEntity<Object>(saveReply, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            entity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }


    // 댓글 삭제
    @RequestMapping(value="/replyDeleteExecute/distName={distName}&replyId={replyId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> replyDeleteExecute(@PathVariable("distName") String distName,
                                                    @PathVariable("replyId") Long replyId){
        ResponseEntity<String> entity = null;

        try {
            Reply replyOne = replyService.getReplyOne(replyId);
            replyService.deleteReply(replyId);
            
            if(distName.equals("notice")){
                noticeService.delReplyCount(replyOne.getNoticeid());
            }

            entity = new ResponseEntity<String>("Delete Success", HttpStatus.OK);

        } catch(Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
    
}