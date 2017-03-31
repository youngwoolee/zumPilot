package com.zum.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zum.domain.Board;
import com.zum.domain.Reply;
import com.zum.domain.User;
import com.zum.repository.ReplyRepository;
import com.zum.service.BoardService;
import com.zum.service.ReplyService;
import com.zum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joeylee on 2017-03-28.
 */
@Controller
@RequestMapping("board/{boardId}")
public class ReplyController {

    Logger logger = LoggerFactory.getLogger(ReplyController.class);

    @Autowired
    ReplyService replyService;
    @Autowired
    UserService uesrService;
    @Autowired
    BoardService boardService;

    @Autowired
    ReplyRepository replyRepository;



    @PostMapping("/reply")
    @ResponseBody
    public List<Reply> replyList(@PathVariable("boardId") Long boardId){

        List<Reply> replyList = replyService.replyListByBoardId(boardId);


        logger.error("replyLIst : " + replyList.toString());

        return replyList;
    }

    @PostMapping("/replyWrite")
    @ResponseBody
    public Reply replyWrite(@PathVariable("boardId") Long boardId,@ModelAttribute Reply reply, Authentication auth){


        logger.error("reply : " + reply.toString());
        User user = uesrService.getUserByUsername(auth.getName());
        Board board = boardService.getBoard(boardId);
        int thread = replyService.getMaxThreadNext(board);

        Reply newReply = replyService.create(reply, board, thread, user);


        logger.error("replyWrite () : " + newReply.toString());
        return newReply;
    }

    @PostMapping("/answerWrite")
    @ResponseBody
    public Reply answerWrite(@PathVariable("boardId") Long boardId, @ModelAttribute Reply reply, @RequestParam("parentId") Long replyId, Authentication auth){

        logger.error("reply : " + reply.toString());
        //부모글 아이디
        logger.error("parentId : " + replyId);
        User user = uesrService.getUserByUsername(auth.getName());
        Board board = boardService.getBoard(boardId);
        Reply parentReply = replyService.getParentReply(replyId);


        //자식들 업데이트
        replyService.updateReply(parentReply.getThread(), replyId, board);


        //댓글 삽입
        Reply newReply = replyService.createAnswer(reply, board,parentReply.getDepth()+1, parentReply.getThread()-1, user);




        logger.error("thread : " + parentReply.getThread());
        logger.error("depth : " + parentReply.getDepth());

        return newReply;
    }

    @PostMapping("/answerModify")
    @ResponseBody
    public Reply answerModify(@PathVariable("boardId") Long boardId, @RequestParam("content") String content, @RequestParam("replyId") Long replyId){

//        logger.error("answerModify() : " + content + ", " + replyId);
        Reply reply = replyService.getParentReply(replyId);
        reply.setContent(content);

        replyRepository.save(reply);


        return reply;
    }

    @PostMapping("/answerDelete")
    @ResponseBody
    public Reply answerDelete(@PathVariable("boardId") Long boardId, @RequestParam("replyId") Long replyId){

//        logger.error("answerModify() : " + content + ", " + replyId);
        replyRepository.deleteReply(replyId);
        Reply deleteReply = replyRepository.findOne(replyId);


        return deleteReply;
    }


}
