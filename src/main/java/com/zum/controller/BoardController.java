package com.zum.controller;

import com.zum.domain.Board;
import com.zum.domain.Image;
import com.zum.domain.Reply;
import com.zum.domain.User;
import com.zum.exception.NotFoundExceptionRest;
import com.zum.service.BoardService;
import com.zum.service.ReplyService;
import com.zum.service.UserService;
import com.zum.util.PageCustomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by joeylee on 2017-03-22.
 */
@Controller
@RequestMapping("/board")
public class BoardController {

    Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReplyService replyService;

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String board(Model model, @RequestParam(value = "pNo", defaultValue = "1") int pNo) {

        PageRequest request = new PageRequest(pNo - 1, 3, Sort.Direction.DESC, "regDate");

        Page<Board> boardList = boardService.getBoardList(request);

        HashMap<String, Object> pageInfo = PageCustomUtil.getPageInfo(boardList, 3, 4, pNo);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageInfo", pageInfo);

        return "board";
    }


    @GetMapping("/new")
    public String boardWriteForm(Model model, Authentication auth) {
        model.addAttribute("auth", auth.getName());
        return "board/boardWrite";
    }

    @PostMapping("/")
    public String write(@Valid Board board,
                        BindingResult bindingResult,
                        MultipartHttpServletRequest multipartRequest,
                        Authentication auth) throws IOException {

        if (bindingResult.hasErrors()) {
            return "/board/boardWrite";
        }

        User user = userService.getUserByUsername(auth.getName());
        boardService.create(board, user, multipartRequest);

        return "redirect:/board/";
    }


    @GetMapping("/{id}")
    public String board(@PathVariable("id") Long id,
                        Model model,
                        Authentication auth) {


        Board board = boardService.getBoard(id);
        Image image = boardService.getImage(id);
        List<Reply> replyLIst = replyService.replyListByBoardId(id);

        model.addAttribute("board", board);
        model.addAttribute("image", image);
        model.addAttribute("auth", auth.getName());
        model.addAttribute("replyList", replyLIst);

        return "board/detail";
    }

    @GetMapping("/{id}/edit")
    public String modifyForm(@PathVariable("id") Long id,
                             Model model,
                             Authentication auth) {

        //권한 체크
        Long userId = boardService.getUserId(id);
        userService.isAuthenticated(auth.getName(), userId);

        Board board = boardService.getBoard(id);
        Image image = boardService.getImage(id);

        model.addAttribute("board", board);
        model.addAttribute("image", image);


        return "board/boardModify";
    }

    @PostMapping("/{id}")
    @ResponseBody
    public ResponseEntity modify(@PathVariable Long id,
                                 @RequestParam("title") String title,
                                 @RequestParam("content") String content,
                                 MultipartHttpServletRequest multipartRequest,
                                 Authentication auth) throws IOException {


        Long userId = boardService.getUserId(id);
        userService.isAuthenticated(auth.getName(), userId);

        boardService.modify(id, title, content, multipartRequest);

        HashMap<String, Object> result = new HashMap<>();
        result.put("url", "/board/");


        return new ResponseEntity<HashMap>(result, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id,
                                 Authentication auth) {

        Long userId = boardService.getUserId(id);
        userService.isAuthenticated(auth.getName(), userId);

        boardService.delete(id);

        HashMap<String, Object> result = new HashMap<>();
        result.put("url", "/board/");

        return new ResponseEntity<HashMap>(result, HttpStatus.OK);
    }


}
