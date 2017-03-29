package com.zum.controller;

import com.zum.domain.Board;
import com.zum.domain.Reply;
import com.zum.domain.User;
import com.zum.repository.ReplyRepository;
import com.zum.service.BoardService;
import com.zum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
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


    private static final int PAGE_SIZE = 3;
    private static final int MAX_PAGER = 4;

    @RequestMapping("/list")
    public String board(Model model, @RequestParam(value="pNo", defaultValue = "1") int pNo) {


        PageRequest request = new PageRequest(pNo -1, PAGE_SIZE, Sort.Direction.DESC, "regDate");
        Page<Board> boardList = boardService.getBoardList(request);
        model.addAttribute("boardList", boardList);
        model.addAttribute("totalPage" , boardList.getTotalPages());
        model.addAttribute("totalElement", boardList.getTotalElements());
        model.addAttribute("pNo", pNo);
        model.addAttribute("pageSize", PAGE_SIZE);
        model.addAttribute("maxPager", MAX_PAGER);

        /*TO DO
        페이지 예외처리 pNo 임의의 파라미터*/


        int totalBlock = (boardList.getTotalPages()-1)/MAX_PAGER+1;
        int currentBlock = (int) Math.ceil(pNo/(double) MAX_PAGER);

        int begin = 1;
        int end = boardList.getTotalPages();

        //게시글이 없을때
        if(boardList.getTotalElements() == 0) {
            end = 0;
        }

        //페이지가 많을때
        if(currentBlock == 1 && totalBlock > 1) {
            //첫 페이지
            begin =1;
            end = MAX_PAGER;
        }

        if(totalBlock == currentBlock && totalBlock > 1) {
            //마지막 페이지
            begin = ( currentBlock * MAX_PAGER) - (MAX_PAGER-1);
            end = boardList.getTotalPages();
        }

        if(totalBlock > currentBlock && totalBlock > 1) {
            //중간 페이지
            begin = ( currentBlock * MAX_PAGER) - (MAX_PAGER-1);
            end = MAX_PAGER + (currentBlock-1) * MAX_PAGER;
        }

        model.addAttribute("begin",begin);
        model.addAttribute("end",end);

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


    @RequestMapping(value ="/modifyForm/{id}")
    public String modifyForm(@PathVariable("id") Long id, Model model) {


        Board board = boardService.getBoard(id);

        model.addAttribute("board", board);


        return "board/boardModify";
    }

    @RequestMapping(value ="/modify", method = RequestMethod.POST)
    public String modify(Board board) {
        logger.error(board.toString());

        if(boardService.update(board)) {

            return "redirect:/board/list";
        }
        else {
            //수정해야함
            logger.error("error");
            return "redirect:/";
        }

    }


    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(Model model) {
        logger.error("aa","bbb");


        return "aaa";
    }



}
