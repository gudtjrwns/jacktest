package com.example.jacktest.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.jacktest.dao.PagingVariables;
import com.example.jacktest.entities.Notice;
import com.example.jacktest.services.NoticeService;
import com.example.jacktest.services.ReplyService;
import com.example.jacktest.utils.ToolsUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private ToolsUtil toolsUtil;


    // 게시판 - 목록
    @RequestMapping("/list")
    public String showNoticeList(ModelMap modelMap,
                                @PageableDefault(size = 10, page = 0) Pageable pageable) {

        Page<Notice> noticePageAll = noticeService.pageAllNotice(pageable);

        if(noticePageAll.isEmpty()){
            modelMap.addAttribute("emptyValue", true);

            return "index";

        }else{
            modelMap.addAttribute("emptyValue", false);
            modelMap.addAttribute("noticeList", noticePageAll);

            // 페이징에 필요한 값 model 전송
            PagingVariables pagingVariables = toolsUtil.returnPagingVariables(noticePageAll, pageable);

            modelMap.addAttribute("totalPages", pagingVariables.getTotalPages());
            modelMap.addAttribute("currentPage", pagingVariables.getPageNumber());
            modelMap.addAttribute("hasNext", pagingVariables.isHasNext());
            modelMap.addAttribute("hasPrevious", pagingVariables.isHasPrevious());
            modelMap.addAttribute("endPage", pagingVariables.getEndPage());
            modelMap.addAttribute("startPage", pagingVariables.getStartPage());
            modelMap.addAttribute("pageNameValue", "list");

            return "index";
        }
    }


    // 게시판 - 검색 목록
    @RequestMapping("/distList")
    public String showNoticeDistList(ModelMap modelMap,
                                    @RequestParam(value="searchType", required = false, defaultValue = "title") String searchType,
                                    @RequestParam(value="searchValue", required = false, defaultValue = "") String searchValue,
                                    @PageableDefault(size = 10, page = 0) Pageable pageable) {

        Page<Notice> noticeDistPage = noticeService.pageAllNoticeDist(pageable, searchType, searchValue);

        if(noticeDistPage.isEmpty()){
            modelMap.addAttribute("emptyValue", true);
            modelMap.addAttribute("searchType", searchType);
            modelMap.addAttribute("searchValue", searchValue);

            return "index";

        } else{
            modelMap.addAttribute("emptyValue", false);
            modelMap.addAttribute("searchType", searchType);
            modelMap.addAttribute("searchValue", searchValue);
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

            return "index";
        }
    }


    // 게시판 - 신규 등록
    @RequestMapping("/add")
    public String showNoticeAdd(ModelMap modelMap) {
        modelMap.addAttribute("notice", new Notice());
        return "add";
    }


    // 게시판 - 신규 등록 - 실행
    @RequestMapping("/addExecute")
    public String showNoticeAddExecute(@Valid Notice notice,
                                        BindingResult bindingResult,
                                        @RequestParam(value = "uploadFile01", required = false, defaultValue = "NONE") MultipartFile file01,
                                        ModelMap modelMap) throws IOException {

        if(bindingResult.hasErrors()){
            modelMap.addAttribute("notice", notice);
            modelMap.addAttribute("uploadFile01", file01);
            return "add";

        } else{
            noticeService.saveNotice(notice, file01); // 정보 추가
            return "redirect:./list";
        }
    }


    // 게시판 - 수정
    @RequestMapping("/edit")
    public String showNoticeEdit(ModelMap modelMap,
                                @RequestParam("noticeId") Long noticeId) {

        Optional<Notice> byId = noticeService.optionalNotice(noticeId);

        if(byId.isPresent()){
            Notice noticeOne = noticeService.getNoticeOne(noticeId);

            modelMap.addAttribute("emptyValue", false);
            modelMap.addAttribute("noticeOne", noticeOne);
            modelMap.addAttribute("noticeId", noticeId);

            return "edit";

        }else{
            modelMap.addAttribute("emptyValue", true);
            return "edit";
        }
    }


    // 게시판 - 수정 - 실행
    @RequestMapping("/editExecute")
    public String showNoticeEditExecute(@Valid Notice notice,
                                        BindingResult bindingResult,
                                        @RequestParam("noticeId") Long noticeId,
                                        @RequestParam(value = "uploadFile01", required = false, defaultValue = "NONE") MultipartFile file01,
                                        ModelMap modelMap) throws IOException {

        if(bindingResult.hasErrors()) {
            modelMap.addAttribute("noticeOne", notice);
            modelMap.addAttribute("uploadFile01", file01);
            return "edit";

        } else{
            noticeService.editNotice(notice, noticeId, file01); // 정보 수정
            return "redirect:./list";
        }
    }


    // 게시판 - 삭제 - 실행
    @RequestMapping("delExecute")
    public String showNoticeDelExecute(@RequestParam("getId") Long noticeId,
                                        ModelMap modelMap) throws IOException {

        noticeService.delFileData(noticeId);                        // 파일 삭제
        noticeService.deleteNoticeOne(noticeId);                    // 게시글 삭제
        replyService.deleteAllByDistIdEquals("notice", noticeId);   // 댓글 삭제

        modelMap.addAttribute("msg", "삭제 성공!");
        modelMap.addAttribute("loc", "/list");

        return "common/msg";
    }


    // 게시판 - 여러 항목 삭제 - 실행
    @RequestMapping("deleteAllExecute")
    public String showNoticeDeleteAllExecute(@RequestParam("checkedArr") List<Long> checkedArr,
                                            ModelMap modelMap) throws IOException {

        noticeService.delAllFileData(checkedArr);   // 파일 삭제
        noticeService.deleteAllNotice(checkedArr);  // 게시글 삭제

        for(int i=0; i<checkedArr.size(); i++){
            replyService.deleteAllByDistIdEquals("notice", checkedArr.get(i));
        }

        modelMap.addAttribute("msg", "삭제 성공!");
        modelMap.addAttribute("loc", "/list");

        return "common/msg";
    }


    // 파일 다운로드
    @RequestMapping(value = "downloadNoticeFileData/id={id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadNoticeFileData(@PathVariable("id") Long id) throws IOException {
        ResponseEntity<InputStreamResource> responseEntity = noticeService.downloadNoticeFile(id);

        return responseEntity;
    }
}
