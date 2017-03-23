package com.zum.controller;

import com.zum.domain.Board;

import com.zum.domain.User;
import com.zum.service.BoardService;
import com.zum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by joeylee on 2017-03-22.
 */
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;
    @Autowired
    UserService userService;

    @RequestMapping("/list")
    public String board(Model model) {
        List<Board> boardList = boardService.getBoardList();
        model.addAttribute("boardList", boardList);

        return "board";
    }

    @RequestMapping("/writeForm")
    public String boardWriteForm(Model model, Authentication auth) {
        model.addAttribute("auth",auth.getName());
        return "board/boardWrite";
    }

    @RequestMapping(value ="/write", method = RequestMethod.POST)
    public String boardWrite(Board board, Authentication auth) {

        User user = userService.getUserByUsername(auth.getName());

        boardService.create(board, user);

        return "redirect:/board/list";
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public String board(@PathVariable("id") Long id, Model model) {

        Board board = boardService.getBoard(id);

        model.addAttribute("board", board);

        return "board/detail";
    }





}
