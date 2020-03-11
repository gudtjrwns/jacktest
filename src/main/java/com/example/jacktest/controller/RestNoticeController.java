package com.example.jacktest.controller;

import com.example.jacktest.dao.NoticeValue;
import com.example.jacktest.entities.Notice;
import com.example.jacktest.repositories.NoticeRepository;
import com.example.jacktest.services.NoticeService;

import org.apache.jasper.tagplugins.jstl.core.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Optional;


@RestController
public class RestNoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeRepository noticeRepository;

    @Value("${notice.uploadPath}")
    private String uploadPath;


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


    @GetMapping("/notice/{id}/file")
    public void downloadFileData(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {

        Optional<Notice> byId = noticeRepository.findById(id);

        if (byId.isPresent()) {
            Notice fileOne = noticeRepository.getOne(id);

            String fullFilePath = uploadPath + "/" + fileOne.getFilepath();

            File file = new File(fullFilePath);

            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileOne.getFilename(), "UTF-8") + "\";");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setContentType("application/download;charset=utf-8");
            response.setContentLengthLong(file.length());
            response.setHeader("Pragma", "no-cache;");
            response.setHeader("Expires", "-1;");
            response.setHeader("Connection", "close");

            FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
        }
    }


}