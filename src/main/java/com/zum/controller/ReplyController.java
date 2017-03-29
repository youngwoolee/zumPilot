package com.zum.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zum.domain.Board;
import com.zum.domain.Reply;
import com.zum.repository.ReplyRepository;
import com.zum.service.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("board/{boardId}/reply")
public class ReplyController {

    Logger logger = LoggerFactory.getLogger(ReplyController.class);

//    @Autowired
//    ReplyService replyService;

    @Autowired
    ReplyService replyService;



    @PostMapping("")
    @ResponseBody
    public List<Reply> replyList(@PathVariable("boardId") Long boardId){

        List<Reply> replyList = replyService.replyListByBoardId(boardId);


        logger.error("replyLIst : " + replyList.toString());

        return replyList;
    }



}
