package com.zum.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.collections.MappingChange;
import com.zum.domain.*;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joeylee on 2017-03-22.
 */
@Controller
@RequestMapping("/board")
public class BoardController {

    private static final int maxPager = 4;
    Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String board(Model model, @RequestParam(value = "pno", defaultValue = "1") int pno) {

        PageRequest request = new PageRequest(pno - 1, 3, Sort.Direction.DESC, "regDate");

        Page<Board> boardList = boardService.getBoardList(request);

        CustomPage customPage = new CustomPage(boardList, 3, 4, pno);

        Map<String, Object> pageInfo = objectMapper.convertValue(customPage, Map.class);

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

        User user = boardService.getUser(id);
        ((SecurityUser) auth.getPrincipal()).authenticated(user);

        Board board = boardService.getBoard(id);
        Image image = boardService.getImage(id);

        model.addAttribute("board", board);
        model.addAttribute("image", image);


        return "board/boardModify";
    }

    @PostMapping("/{id}")
    @ResponseBody
    public ResponseEntity modify(@PathVariable Long id,
                                 Board board,
                                 MultipartHttpServletRequest multipartRequest,
                                 Authentication auth) throws IOException {

        User user = boardService.getUser(id);
        ((SecurityUser) auth.getPrincipal()).authenticated(user);

        boardService.modify(id, board, multipartRequest);

        HashMap<String, Object> result = new HashMap<>();
        result.put("url", "/board/");

        return new ResponseEntity<HashMap>(result, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") Long id,
                                 Authentication auth) {

        User user = boardService.getUser(id);
        ((SecurityUser) auth.getPrincipal()).authenticated(user);

        boardService.delete(id);

        HashMap<String, Object> result = new HashMap<>();
        result.put("url", "/board/");

        return new ResponseEntity<HashMap>(result, HttpStatus.OK);
    }
}
