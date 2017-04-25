package com.zum.controller;

import com.zum.domain.Reply;
import com.zum.domain.SecurityUser;
import com.zum.domain.User;
import com.zum.service.ReplyService;
import com.zum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by joeylee on 2017-03-28.
 */
@RestController
@RequestMapping("/board/{boardId}")
public class ReplyController {

    Logger logger = LoggerFactory.getLogger(ReplyController.class);

    @Autowired
    private ReplyService replyService;

    @PostMapping("/reply/create")
    public Reply replyWrite(@PathVariable("boardId") Long boardId,
                            @ModelAttribute Reply reply,
                            Authentication auth){

        Reply newReply = replyService.create(reply, boardId, auth.getName());

        return newReply;
    }

    @PostMapping("/answer/create")
    public Reply answerWrite(@PathVariable("boardId") Long boardId,
                             @ModelAttribute Reply reply,
                             @RequestParam("parentId") Long replyId,
                             Authentication auth){

        replyService.updateReply(replyId, boardId);
        Reply newReply = replyService.createAnswer(reply, boardId, replyId, auth.getName());

        return newReply;
    }

    @PostMapping("/answerModify")
    public Reply answerModify(@PathVariable("boardId") Long boardId,
                              @RequestParam("content") String content,
                              @RequestParam("replyId") Long replyId,
                              Authentication auth){

        User user = replyService.getUser(replyId);
        ((SecurityUser) auth.getPrincipal()).authenticated(user);

        return replyService.modifyAnswer(content, replyId);
    }

    @PostMapping("/answerDelete")
    public Reply answerDelete(@PathVariable("boardId") Long boardId,
                              @RequestParam("replyId") Long replyId,
                              Authentication auth){

        User user = replyService.getUser(replyId);
        ((SecurityUser) auth.getPrincipal()).authenticated(user);

        Reply reply = replyService.deleteReply(replyId);
        return reply;
    }
}
