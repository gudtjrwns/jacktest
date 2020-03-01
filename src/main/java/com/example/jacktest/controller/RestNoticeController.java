package com.example.jacktest.controller;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import com.example.jacktest.dao.NoticeValue;
import com.example.jacktest.services.NoticeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class RestNoticeController {

    @Autowired
    private NoticeService noticeService;

    
    // 게시글 제목 중복 확인
    @GetMapping("/findByTitleEquals/title={title}")
    public boolean findByTitleEquals(@PathVariable("title") String title) {

        boolean existsByTitleEquals = noticeService.lookingForTitleEquals(title);
        return existsByTitleEquals;
    }


    // 게시글 정보 가져오기
    @PostMapping("/findByNoticeOne/id={id}")
    public NoticeValue findByNoticeOne(@PathVariable("id") Long id) {
        
        // 조회수 추가
        noticeService.addViewCount(id);

        NoticeValue noticeValueOne = noticeService.getNoticeValue(id);
        
        return noticeValueOne;
    }


    // 파일 다운로드
    @GetMapping(value="/downloadNoticeFileData/id={noticeId}")
    public ResponseEntity<InputStreamResource> downloadNoticeFileData(@PathVariable("noticeId") Long noticeId) throws FileNotFoundException, UnsupportedEncodingException {

        ResponseEntity<InputStreamResource> responseEntity = noticeService.downloadNoticeFile(noticeId);
        return responseEntity;
    }
    
    
    
}