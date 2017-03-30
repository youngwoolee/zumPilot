package com.zum.service;

import com.zum.domain.Board;
import com.zum.domain.Reply;
import com.zum.domain.User;
import com.zum.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by joeylee on 2017-03-29.
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    private static final int LIMIT_REPLY = 1000;

    @Autowired
    ReplyRepository replyRepository;

    @Override
    public List<Reply> replyListByBoardId(Long boardId) {
        List<Reply> replyList = replyRepository.findByBoardBoardIdOrderByThreadDesc(boardId);


        return replyList;
    }



    @Override
    public Reply create(Reply reply, Board board, int thread, User user) {

        reply.setWriter(user);
        reply.setThread(thread);
        reply.setBoard(board);

        replyRepository.save(reply);

        return reply;
    }

    @Override
    public int getMaxThreadNext(Board board) {
        int maxThreadNextValue = replyRepository.getMaxThread(board) + LIMIT_REPLY;

        return maxThreadNextValue;
    }

}
