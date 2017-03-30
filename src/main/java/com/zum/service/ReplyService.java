package com.zum.service;

import com.zum.domain.Board;
import com.zum.domain.Reply;
import com.zum.domain.User;

import java.util.List;

/**
 * Created by joeylee on 2017-03-29.
 */
public interface ReplyService {

    List<Reply> replyListByBoardId(Long boardId);
    Reply create(Reply reply, Board board, int thread, User user);
    Reply createAnswer(Reply reply, Board board, int depth, int thread, User user);
    int getMaxThreadNext(Board board);

    Reply getParentReply(Long replyId);

//    void updateReply(int parentThread, Long replyId);
}
