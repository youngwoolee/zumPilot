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
    Reply create(Reply reply, Long boardId, String username);
    Reply createAnswer(Reply reply, Long boardId, Long replyId, String username);
    int getMaxThreadNext(Board board);

    Reply getParentReply(Long replyId);

    void updateReply(Long replyId, Long boardId);

    Reply modifyAnswer(String content, Long replyId);

    Long getUserId(Long replyId);

    Reply deleteReply(Long replyId);


}
