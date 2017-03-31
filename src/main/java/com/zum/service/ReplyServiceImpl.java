package com.zum.service;

import com.zum.domain.Board;
import com.zum.domain.Reply;
import com.zum.domain.User;
import com.zum.repository.ReplyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(ReplyServiceImpl.class);

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
    public Reply createAnswer(Reply reply, Board board, int depth, int thread, User user) {
        reply.setWriter(user);
        reply.setThread(thread);
        reply.setBoard(board);
        reply.setDepth(depth);

        replyRepository.save(reply);

        return reply;
    }

    @Override
    public int getMaxThreadNext(Board board) {
        int maxThreadNextValue = replyRepository.getMaxThread(board) + LIMIT_REPLY;

        return maxThreadNextValue;
    }


    @Override
    public Reply getParentReply(Long replyId) {

        Reply parentReply = replyRepository.findByReplyId(replyId);



        return parentReply;
    }

    @Override
    public void updateReply(int parentThread, Long replyId, Board board) {

        Reply reply = replyRepository.findByReplyId(replyId);
        int prevReplyThread = 0;

        //이전 reply 의 스레드
        if(reply.getThread() != 0 ) {
            prevReplyThread = replyRepository.getPrevReplyThread(replyId);
        }

        logger.error("real : " + parentThread +"," + prevReplyThread);
        List<Reply> updateReplyList = replyRepository.getReplyIdBetweenPrevCurrent(parentThread, prevReplyThread, board);

        for(int i=0; i < updateReplyList.size(); i++) {
            logger.error("update replyId : " + updateReplyList.get(i).getReplyId());
        }

        //updateReplyThread
        for(int i=0; i < updateReplyList.size(); i++) {
            replyRepository.updateReplyThread(updateReplyList.get(i).getReplyId());
        }

    }
}
