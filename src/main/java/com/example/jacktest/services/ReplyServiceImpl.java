package com.example.jacktest.services;

import java.util.List;

import com.example.jacktest.entities.Reply;
import com.example.jacktest.repositories.ReplyRepository;
import com.example.jacktest.utils.ToolsUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private ToolsUtil toolsUtil;


    // 정보
    public Reply getReplyOneByDistIdEquals(String distName, Long distId) {
        
        if(distName.equals("notice")){
            Reply replyOne = replyRepository.findByNoticeidEquals(distId);
            return replyOne;
        }else{
            return null;
        }
    }


    public Reply getReplyOne(Long replyId){
        Reply replyOne = replyRepository.getOne(replyId);
        return replyOne;
    }


    // 목록
    public List<Reply> listAllReplyByDistIdEquals(String distName, Long distId) {

        if(distName.equals("notice")){
            List<Reply> replyList = replyRepository.findAllByNoticeidEqualsOrderByCredateAsc(distId);
            return replyList;
        } else {
            return null;
        }
    }


    // 저장
    public Reply saveReply(String distName, Long distId, String writer, String contents) {

        Reply replyDB = new Reply();

        replyDB.setWriter(writer);
        replyDB.setContents(contents);
        replyDB.setCredate(toolsUtil.currentDateInTimestamp());
        replyDB.setModdate(toolsUtil.currentDateInTimestamp());

        if(distName.equals("notice")){
            replyDB.setNoticeid(distId);
        }
        
        Reply saveReply = replyRepository.save(replyDB);

        return saveReply;
    }


    // 수정
    public Reply editReply(Long replyId, String contents){

        Reply replyOne = replyRepository.getOne(replyId);

        replyOne.setContents(contents);

        Reply saveReply = replyRepository.save(replyOne);

        return saveReply;
    }


    // 삭제
    public void deleteReply(Long replyId) {

        replyRepository.deleteById(replyId);
    }

    
    public void deleteAllByDistIdEquals(String distName, Long distId){

        if(distName.equals("notice")){
            List<Reply> replyList = replyRepository.findAllByNoticeidEquals(distId);
            
            for(int i=0; i<replyList.size(); i++){
                replyRepository.deleteById(replyList.get(i).getId());
            }
        }
    }

}