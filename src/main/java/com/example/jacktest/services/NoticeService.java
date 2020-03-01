package com.example.jacktest.services;

import com.example.jacktest.dao.NoticeValue;
import com.example.jacktest.entities.Notice;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

public interface NoticeService {

    public Page<Notice> pageAllNotice(Pageable Pageable);

    public Page<Notice> pageAllNoticeDist(Pageable pageable, String searchType, String searchValue);

    public Notice saveNotice(Notice notice, MultipartFile file01) throws IOException;

    public Notice editNotice(Notice notice, Long id, MultipartFile file01) throws IOException;

    public Notice getNoticeOne(Long id);

    public NoticeValue getNoticeValue(Long id);

    public Optional<Notice> optionalNotice(Long id);

    public void deleteNoticeOne(Long id);

    public void deleteAllNotice(List<Long> idList);

    public void addViewCount(Long id);

    public void addReplyCount(Long id);

    public void delReplyCount(Long id);

    public boolean lookingForTitleEquals(String title);

    public boolean delFileData(Long id) throws IOException;

    public void delAllFileData(List<Long> idList) throws IOException;

    public ResponseEntity<InputStreamResource> downloadNoticeFile(Long noticeId) throws FileNotFoundException, UnsupportedEncodingException;
}
