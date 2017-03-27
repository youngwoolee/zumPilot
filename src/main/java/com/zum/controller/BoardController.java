package com.zum.controller;

import com.zum.domain.Board;
import com.zum.domain.User;
import com.zum.service.BoardService;
import com.zum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by joeylee on 2017-03-22.
 */
@Controller
@RequestMapping("/board")
public class BoardController {

    Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    BoardService boardService;
    @Autowired
    UserService userService;

    private static final int PAGE_SIZE = 5;

    @RequestMapping("/list")
    public String board(Model model, @RequestParam(value="pNo", defaultValue = "1") int pNo) {


        PageRequest request = new PageRequest(pNo -1, 5, Sort.Direction.DESC, "regDate");
        Page<Board> boardList = boardService.getBoardList(request);
        model.addAttribute("boardList", boardList);
        model.addAttribute("totalPage" , boardList.getTotalPages());

        logger.error("totalPage : " + boardList.getTotalPages());

        return "board";
    }


    @RequestMapping("/writeForm")
    public String boardWriteForm(Model model, Authentication auth) {
        model.addAttribute("auth",auth.getName());
        return "board/boardWrite";
    }

    @RequestMapping(value ="/write", method = RequestMethod.POST)
    public String boardWrite(Board board, Authentication auth) {

        logger.error(board.toString());
        User user = userService.getUserByUsername(auth.getName());

        boardService.create(board, user);

        return "redirect:/board/list";
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public String board(@PathVariable("id") Long id, Model model, Authentication auth) {

        boardService.increaseHit(id);

        Board board = boardService.getBoard(id);

        model.addAttribute("board", board);
        model.addAttribute("auth", auth.getName());

        return "board/detail";
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(Model model) {
        logger.error("aa","bbb");


        return "aaa";
    }





}
