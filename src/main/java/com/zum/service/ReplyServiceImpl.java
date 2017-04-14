package com.zum.service;

import com.zum.domain.Board;
import com.zum.domain.Reply;
import com.zum.domain.User;
import com.zum.repository.BoardRepository;
import com.zum.repository.ReplyRepository;
import com.zum.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by joeylee on 2017-03-29.
 */
@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

    private static final int LIMIT_REPLY = 1000;

    Logger logger = LoggerFactory.getLogger(ReplyServiceImpl.class);

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public List<Reply> replyListByBoardId(Long boardId) {
        List<Reply> replyList = replyRepository.findByBoardBoardIdOrderByThreadDesc(boardId);

        return replyList;
    }


    @Override
    public Reply create(Reply reply, Long boardId, String username) {

        User user = userRepository.findByUserName(username);
        Board board = boardRepository.findByBoardId(boardId);
        int thread = replyRepository.getMaxThreadByBoardId(boardId) + LIMIT_REPLY;

        reply.createReply(board,user,thread);
        replyRepository.save(reply);

        return reply;
    }

    @Override
    public Reply createAnswer(Reply reply, Long boardId, Long replyId, String username) {

        User user = userRepository.findByUserName(username);
        Board board = boardRepository.findByBoardId(boardId);
        Reply parentReply = getParentReply(replyId);

        reply.createAnswer(user, board, parentReply.getThread()-1, parentReply.getDepth()+1);

        replyRepository.save(reply);

        return reply;
    }

    @Override
    public int getMaxThreadNext(Board board) {

        return replyRepository.getMaxThread(board) + LIMIT_REPLY;
    }


    @Override
    public Reply getParentReply(Long replyId) {


        return replyRepository.findByReplyId(replyId);
    }

    @Override
    public void updateReply(Long replyId, Long boardId) {

        Reply reply = replyRepository.findByReplyId(replyId);

        int prevReplyThread = 0;
        int parentThread = getParentReply(replyId).getThread();

        //이전 reply 의 스레드
        if (reply.getThread() != 0) {
            prevReplyThread = replyRepository.getPrevReplyThread(replyId);
        }

        logger.error("real : {}", parentThread, prevReplyThread);
        List<Reply> updateReplyList = replyRepository.getReplyIdBetweenPrevCurrent(parentThread, prevReplyThread, boardId);

        //updateReplyThread
        for (Reply anUpdateReplyList : updateReplyList) {
            replyRepository.updateReplyThread(anUpdateReplyList.getReplyId());
        }

    }

    @Override
    public Reply modifyAnswer(String content, Long replyId) {

    Reply reply = getParentReply(replyId);
    reply.setContent(content);

    replyRepository.save(reply);

    return reply;

}

    @Override
    public Long getUserId(Long replyId) {

        Reply reply = replyRepository.findByReplyId(replyId);

        return reply.getWriter().getUserId();
    }

    @Override
    public Reply deleteReply(Long replyId) {

        Reply deleteReply = replyRepository.findByReplyId(replyId);

        deleteReply.updateStatus();

        replyRepository.save(deleteReply);

        return deleteReply;
    }

}
