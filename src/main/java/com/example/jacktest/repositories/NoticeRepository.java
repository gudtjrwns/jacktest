package com.example.jacktest.repositories;

import com.example.jacktest.entities.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    // 전체보기
    Page<Notice> findAllByOrderByCredateDesc(Pageable pageable);

    // 검색 - 제목
    Page<Notice> findAllByTitleContainsOrderByCredateDesc(String title, Pageable pageable);

    // 검색 - 내용
    Page<Notice> findAllByContentsContainsOrderByCredateDesc(String contents, Pageable pageable);

    // 검색 - 이름
    Page<Notice> findAllByWriterContainsOrderByCredateDesc(String writer, Pageable pageable);

    // 검색 - 제목+내용
    Page<Notice> findAllByTitleContainsOrContentsContainsOrderByCredateDesc(String title, String contents, Pageable pageable);

    // 검색 - 내용+작성자
    Page<Notice> findAllByContentsContainsOrWriterContainsOrderByCredateDesc(String contents, String writer, Pageable pageable);

    // 검색 - 제목+내용+작성자
    Page<Notice> findAllByTitleContainsOrContentsContainsOrWriterContainsOrderByCredateDesc(String title, String contents, String writer, Pageable pageable);

    // 제목 중복 확인
    boolean existsByTitleEquals(String title);
}
