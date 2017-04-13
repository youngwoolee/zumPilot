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
import org.springframework.http.HttpStatus;
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
@RestController
@RequestMapping("/board/{boardId}")
public class ReplyController {

    Logger logger = LoggerFactory.getLogger(ReplyController.class);

    @Autowired
    private ReplyService replyService;
    @Autowired
    private UserService userService;

    @PostMapping("/reply")
    public List<Reply> replyList(@PathVariable("boardId") Long boardId){
        List<Reply> replyList = replyService.replyListByBoardId(boardId);

        logger.error("replyLIst : {} ", replyList.toString());

        return replyList;
    }

    @PostMapping("/reply/create")
    public Reply replyWrite(@PathVariable("boardId") Long boardId,
                            @ModelAttribute Reply reply,
                            Authentication auth){


        Reply newReply = replyService.create(reply, boardId, auth.getName());

        logger.error("replyWrite : {} ",newReply.toString());
        return newReply;
    }

    @PostMapping("/answer/create")
    public Reply answerWrite(@PathVariable("boardId") Long boardId,
                             @ModelAttribute Reply reply,
                             @RequestParam("parentId") Long replyId,
                             Authentication auth){


        //자식들 업데이트
        replyService.updateReply(replyId, boardId);
        //댓글 삽입
        Reply newReply = replyService.createAnswer(reply, boardId, replyId, auth.getName());

        return newReply;
    }

    @PostMapping("/answerModify")
    public Reply answerModify(@PathVariable("boardId") Long boardId,
                              @RequestParam("content") String content,
                              @RequestParam("replyId") Long replyId,
                              Authentication auth){

        Long userId = replyService.getUserId(replyId);
        userService.isAuthenticated(auth.getName(), userId);

        return replyService.modifyAnswer(content, replyId);
    }

    @PostMapping("/answerDelete")
    @ResponseBody
    public Reply answerDelete(@PathVariable("boardId") Long boardId,
                              @RequestParam("replyId") Long replyId,
                              Authentication auth){

        Long userId = replyService.getUserId(replyId);
        userService.isAuthenticated(auth.getName(), userId);


        return replyService.deleteReply(replyId);
    }


}
