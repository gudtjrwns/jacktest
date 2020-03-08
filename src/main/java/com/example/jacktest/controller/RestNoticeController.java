package com.example.jacktest.controller;

import com.example.jacktest.dao.NoticeValue;
import com.example.jacktest.dao.PagingVariables;
import com.example.jacktest.entities.Notice;
import com.example.jacktest.services.NoticeService;

import com.example.jacktest.services.ReplyService;
import com.example.jacktest.utils.ToolsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
public class RestNoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private ToolsUtil toolsUtil;


    // 게시글 제목 중복 확인
    @GetMapping("/findByTitleEquals/title={title}")
    public boolean findByTitleEquals(@PathVariable("title") String title) {

        boolean existsByTitleEquals = noticeService.lookingForTitleEquals(title);
        return existsByTitleEquals;
    }


    // 게시글 정보 가져오기
    @GetMapping("/findByNoticeOne/noticeId={noticeId}")
    public ResponseEntity findByNoticeOne(@PathVariable("noticeId") Long noticeId) {

        Optional<Notice> byId = noticeService.optionalNotice(noticeId);

        if (byId.isPresent()) {
            Notice noticeOne = noticeService.getNoticeOne(noticeId);
            return ResponseEntity.ok(noticeOne);
        } else {
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        }
    }


    // 조회수 추가 and 게시글 정보 가져오기
    @PostMapping("/findByNoticeOne/id={id}")
    public ResponseEntity findByNoticeOneAndAddViewcount(@PathVariable("id") Long id) {

        // 조회수 추가
        noticeService.addViewCount(id);

        NoticeValue noticeValueOne = noticeService.getNoticeValue(id);

        return ResponseEntity.ok(noticeValueOne);
    }


    // 게시판 - 목록
    @GetMapping(value = "/getNoticePage")
    public ResponseEntity showNoticeList(ModelMap modelMap,
                                         @PageableDefault(size = 10, page = 0) Pageable pageable) {

        Page<Notice> noticePage = noticeService.pageAllNotice(pageable);

        if (noticePage.isEmpty()) {
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);

        } else {
            modelMap.addAttribute("noticeList", noticePage);

            // 페이징에 필요한 값 model 전송
            PagingVariables pagingVariables = toolsUtil.returnPagingVariables(noticePage, pageable);

            modelMap.addAttribute("totalPages", pagingVariables.getTotalPages());
            modelMap.addAttribute("currentPage", pagingVariables.getPageNumber());
            modelMap.addAttribute("hasNext", pagingVariables.isHasNext());
            modelMap.addAttribute("hasPrevious", pagingVariables.isHasPrevious());
            modelMap.addAttribute("endPage", pagingVariables.getEndPage());
            modelMap.addAttribute("startPage", pagingVariables.getStartPage());
            modelMap.addAttribute("pageNameValue", "list");

            return ResponseEntity.ok(modelMap);
        }
    }


    // 게시판 - 검색 목록
    @GetMapping(value = "/getNoticeDistPage/searchType={searchType}&searchValue={searchValue}")
    public ResponseEntity showNoticeDistList(ModelMap modelMap,
                                             @PathVariable(value="searchType", required = false) String searchType,
                                             @PathVariable(value="searchValue", required = false) String searchValue,
                                             @PageableDefault(size = 10, page = 0) Pageable pageable) {

        Page<Notice> noticeDistPage = noticeService.pageAllNoticeDist(pageable, searchType, searchValue);

        if (noticeDistPage.isEmpty()) {
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);

        } else {
            modelMap.addAttribute("noticeList", noticeDistPage);

            // 페이징에 필요한 값 model 전송
            PagingVariables pagingVariables = toolsUtil.returnPagingVariables(noticeDistPage, pageable);

            modelMap.addAttribute("totalPages", pagingVariables.getTotalPages());
            modelMap.addAttribute("currentPage", pagingVariables.getPageNumber());
            modelMap.addAttribute("hasNext", pagingVariables.isHasNext());
            modelMap.addAttribute("hasPrevious", pagingVariables.isHasPrevious());
            modelMap.addAttribute("endPage", pagingVariables.getEndPage());
            modelMap.addAttribute("startPage", pagingVariables.getStartPage());
            modelMap.addAttribute("pageNameValue", "distList");

            return ResponseEntity.ok(modelMap);
        }
    }


    // 게시판 - 신규 등록 - 실행
    @PostMapping(value = "/addExecute")
    public ResponseEntity showNoticeAddExecute(@Valid Notice notice,
                                               BindingResult bindingResult,
                                               @RequestParam(value = "uploadFile01", required = false, defaultValue = "NONE") MultipartFile file01) throws IOException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);

        } else {
            noticeService.saveNotice(notice, file01);
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }


    // 게시판 - 수정 - 실행
    @PutMapping(value = "/editExecute/noticeId={noticeId}")
    public ResponseEntity showNoticeEditExecute(@Valid Notice notice,
                                                BindingResult bindingResult,
                                                @PathVariable("noticeId") Long noticeId,
                                                @RequestParam(value = "uploadFile01", required = false, defaultValue = "NONE") MultipartFile file01,
                                                ModelMap modelMap) throws IOException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);

        } else {
            noticeService.editNotice(notice, noticeId, file01);
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }


    // 게시판 - 삭제
    @DeleteMapping(value = "/deleteExecute/noticeId={noticeId}")
    public ResponseEntity showNoticeDeleteExecute(@PathVariable("noticeId") Long noticeId) throws IOException {

        noticeService.delFileData(noticeId);                        // 파일 삭제
        noticeService.deleteNoticeOne(noticeId);                    // 게시글 삭제
        replyService.deleteAllByDistIdEquals("notice", noticeId);   // 댓글 삭제

        return ResponseEntity.ok(HttpStatus.OK);
    }


    // 게시판 - 여러 항목 삭제
    @DeleteMapping(value = "/deleteAllExecute/noticeIdList={idList}")
    public ResponseEntity showNoticeDeleteAllExecute(@PathVariable("idList") List<Long> idList) throws IOException {

        noticeService.delAllFileData(idList);   // 파일 삭제
        noticeService.deleteAllNotice(idList);  // 게시글 삭제

        for (int i = 0; i < idList.size(); i++) {
            replyService.deleteAllByDistIdEquals("notice", idList.get(i));
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }


    // 게시판 - 파일 다운로드
    @GetMapping(value = "/downloadNoticeFileData/noticeId={noticeId}", produces = "application/text; charset=utf8")
    public ResponseEntity<InputStreamResource> showNoticeFileDataDownload(@PathVariable("noticeId") Long noticeId) throws IOException {
        ResponseEntity<InputStreamResource> responseEntity = noticeService.downloadNoticeFile(noticeId);
        return responseEntity;
    }


}