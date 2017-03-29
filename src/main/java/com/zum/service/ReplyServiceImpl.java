package com.zum.service;

import com.zum.domain.Reply;
import com.zum.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by joeylee on 2017-03-29.
 */
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    ReplyRepository replyRepository;

//    @Override
//    public List<Reply> replyListByBoardId(Long boardId) {
//        List<Reply> replyList = replyRepository.
//                findByBoardBoardIdOrderByReplyThreadDesc(boardId);
//
//
//        return replyList;
//    }
}
