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

//    @Autowired
//    ReplyService replyService;

    @Autowired
    ReplyService replyService;
    @Autowired
    UserService uesrService;
    @Autowired
    BoardService boardService;



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


        logger.error(newReply.toString());
        return newReply;
    }



}
