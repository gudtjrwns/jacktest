package com.example.jacktest.services;

import java.util.List;

import com.example.jacktest.entities.Reply;

public interface ReplyService {

    // 정보
    public Reply getReplyOneByDistIdEquals(String distName, Long distId);

    public Reply getReplyOne(Long replyId);

    // 목록
    public List<Reply> listAllReplyByDistIdEquals(String distName, Long distId);

    // 저장
    public Reply saveReply(String distName, Long distId, String writer, String contents);

    // 수정
    public Reply editReply(Long replyId, String contents);

    // 삭제
    public void deleteReply(Long replyId);

    public void deleteAllByDistIdEquals(String distName, Long distId);
}