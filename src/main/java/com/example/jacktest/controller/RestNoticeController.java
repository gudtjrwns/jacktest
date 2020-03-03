package com.example.jacktest.controller;

import com.example.jacktest.dao.NoticeValue;
import com.example.jacktest.entities.Notice;
import com.example.jacktest.services.NoticeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


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


    // 게시판 - 목록
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public ResponseEntity<Page<Notice>> showNoticeList(ModelMap modelMap,
//                                                       @PageableDefault(size = 10, page = 0) Pageable pageable) {
//
//        Page<Notice> noticeList = noticeService.pageAllNotice(pageable);
//
//        if (noticeList.isEmpty()) {
//
//
//        } else {
//        }
//
//
//    }

    // 게시판 - 검색 목록


    // 게시판 - 신규 등록


    // 게시판 - 수정


    // 게시판 - 삭제


    // 게시판 - 여러 항목 삭제


    // 게시판 - 파일 다운로드


}