package com.example.jacktest.repositories;

import java.util.List;

import com.example.jacktest.entities.Reply;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // 목록 - 게시판
    List<Reply> findAllByNoticeidEqualsOrderByCredateAsc(Long distId);

    List<Reply> findAllByNoticeidEquals(Long distId);

    // 정보 찾기 - 게시판
    Reply findByNoticeidEquals(Long noticeId);
}