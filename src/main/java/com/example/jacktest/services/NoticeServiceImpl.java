package com.example.jacktest.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

import com.example.jacktest.dao.NoticeValue;
import com.example.jacktest.entities.Notice;
import com.example.jacktest.repositories.NoticeRepository;
import com.example.jacktest.utils.ToolsUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private ToolsUtil toolsUtil;

    @Value("${notice.uploadPath}")
    private String uploadPath;

    

    public Page<Notice> pageAllNotice(Pageable Pageable) {
        Page<Notice> noticePage = noticeRepository.findAllByOrderByCredateDesc(Pageable);
        return noticePage;
    }


    public Page<Notice> pageAllNoticeDist(Pageable pageable, String searchType, String searchValue){

        if(searchType.equals("title")){ // 검색 - 제목
            Page<Notice> noticeDistPage = noticeRepository.findAllByTitleContainsOrderByCredateDesc(searchValue, pageable);
            return noticeDistPage;

        } else if (searchType.equals("contents")) { // 검색 - 내용
            Page<Notice> noticeDistPage = noticeRepository.findAllByContentsContainsOrderByCredateDesc(searchValue, pageable);
            return noticeDistPage;

        } else if (searchType.equals("writer")){ // 검색 - 작성자
            Page<Notice> noticeDistPage = noticeRepository.findAllByWriterContainsOrderByCredateDesc(searchValue, pageable);
            return noticeDistPage;

        } else if (searchType.equals("titleAndContents")) { // 검색 - 제목+내용
            Page<Notice> noticeDistPage = noticeRepository.findAllByTitleContainsOrContentsContainsOrderByCredateDesc(searchValue, searchValue, pageable);
            return noticeDistPage;

        } else if (searchType.equals("contentsAndWriter")) { // 검색 - 내용+작성자
            Page<Notice> noticeDistPage = noticeRepository.findAllByContentsContainsOrWriterContainsOrderByCredateDesc(searchValue, searchValue, pageable);
            return noticeDistPage;

        } else { // 검색 - 제목+내용+작성자
            Page<Notice> noticeDistPage = noticeRepository.findAllByTitleContainsOrContentsContainsOrWriterContainsOrderByCredateDesc(searchValue, searchValue, searchValue, pageable);
            return noticeDistPage;
        }
        
    }


    public Notice saveNotice(Notice notice, MultipartFile file01) throws IOException {
        
        Notice noticeDB = new Notice();

        noticeDB.setTitle(notice.getTitle());
        noticeDB.setContents(notice.getContents());
        noticeDB.setWriter(notice.getWriter());
        noticeDB.setViewcnt(0);
        noticeDB.setReplycnt(0);

        if (file01.isEmpty()) { // 파일 저장
            noticeDB.setFilename("NONE");
            noticeDB.setFilepath("NONE");
        } else {
            noticeDB.setFilename(file01.getOriginalFilename());
            noticeDB.setFilepath(toolsUtil.uploadFile(file01, uploadPath));
        }

        noticeDB.setCredate(toolsUtil.currentDateInTimestamp());
        noticeDB.setModdate(toolsUtil.currentDateInTimestamp());

        Notice saveNoticeOne = noticeRepository.save(noticeDB);

        return saveNoticeOne;
    }


    public Notice editNotice(Notice notice, Long id, MultipartFile file01) throws IOException {

        Notice noticeOne = noticeRepository.getOne(id);

        noticeOne.setTitle(notice.getTitle());
        noticeOne.setContents(notice.getContents());
        noticeOne.setModdate(toolsUtil.currentDateInTimestamp());

        if (!file01.isEmpty()) { // 파일 저장
            noticeOne.setFilename(file01.getOriginalFilename());
            noticeOne.setFilepath(toolsUtil.uploadFile(file01, uploadPath));

            toolsUtil.deleteFileIfExists(noticeOne.getFilepath(), uploadPath); // 기존 파일 삭제
        }

        Notice saveNoticeOne = noticeRepository.save(noticeOne);

        return saveNoticeOne;
    }


    public Notice getNoticeOne(Long id){
        Notice noticeOne = noticeRepository.getOne(id);
        return noticeOne;
    }

    
    public NoticeValue getNoticeValue(Long id) {
        
        Notice noticeOne = noticeRepository.getOne(id);

        NoticeValue noticeValueOne = new NoticeValue();

        noticeValueOne.setId(noticeOne.getId());
        noticeValueOne.setTitle(noticeOne.getTitle());
        noticeValueOne.setContents(noticeOne.getContents());
        noticeValueOne.setWriter(noticeOne.getWriter());
        noticeValueOne.setViewcnt(noticeOne.getViewcnt());
        noticeValueOne.setReplycnt(noticeOne.getReplycnt());
        noticeValueOne.setFilename(noticeOne.getFilename());
        noticeValueOne.setFilepath(noticeOne.getFilepath());
        noticeValueOne.setCredate(toolsUtil.timestampToString(noticeOne.getCredate()));
        noticeValueOne.setModdate(toolsUtil.timestampToString(noticeOne.getModdate()));

        return noticeValueOne;
    }


    public Optional<Notice> optionalNotice(Long id) {
        Optional<Notice> byId =  noticeRepository.findById(id);
        return byId;
    }


    public void deleteNoticeOne(Long id) {
        noticeRepository.deleteById(id);
    }


    public void deleteAllNotice(List<Long> idList) {
        
        for(int i=0; i<idList.size(); i++){
            noticeRepository.deleteById(idList.get(i));
        }
    }


    public void addViewCount(Long id) {
        Notice noticeOne = noticeRepository.getOne(id);

        noticeOne.setViewcnt(noticeOne.getViewcnt()+1);

        noticeRepository.save(noticeOne);
    }


    public void addReplyCount(Long id){
        Notice noticeOne = noticeRepository.getOne(id);

        noticeOne.setReplycnt(noticeOne.getReplycnt()+1);

        noticeRepository.save(noticeOne);
    }

    
    public void delReplyCount(Long id){
        Notice noticeOne = noticeRepository.getOne(id);

        noticeOne.setReplycnt(noticeOne.getReplycnt()-1);

        noticeRepository.save(noticeOne);
    }


    public boolean lookingForTitleEquals(String title) {
        boolean existsByTitleEquals = noticeRepository.existsByTitleEquals(title);
        return existsByTitleEquals;
    }


    public boolean delFileData(Long id) throws IOException {
        
        Optional<Notice> byId = noticeRepository.findById(id);

        if(byId.isPresent()){
            Notice noticeOne = noticeRepository.getOne(id);
            toolsUtil.deleteFileIfExists(noticeOne.getFilepath(), uploadPath);
            return true;

        } else {
            return false;
        }
    }


    public void delAllFileData(List<Long> idList) throws IOException {

        for(int i=0; i<idList.size(); i++) {
            delFileData(idList.get(i));
        }
    }


    public ResponseEntity<InputStreamResource> downloadNoticeFile(Long noticeId) throws FileNotFoundException, UnsupportedEncodingException {

        Optional<Notice> byId = noticeRepository.findById(noticeId);

        if(byId.isPresent()) {
            Notice fileOne = noticeRepository.getOne(noticeId);

            String fileFullPath = uploadPath + "/" + fileOne.getFilepath();

            File file = new File(fileFullPath);

            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, 
                            "attachment;filename=" + URLEncoder.encode(fileOne.getFilepath(), "UTF-8"))
                    .contentType(MediaType.APPLICATION_PDF).contentLength(file.length())
                    .body(resource);

        } else {
            return null;
        }
    }
}