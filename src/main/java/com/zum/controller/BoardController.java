package com.zum.controller;

import com.zum.domain.Board;
import com.zum.domain.Image;
import com.zum.domain.Reply;
import com.zum.domain.User;
import com.zum.repository.BoardRepository;
import com.zum.repository.ImageRepository;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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
    BoardRepository boardRepository;
    @Autowired
    UserService userService;
    @Autowired
    ImageRepository imageRepository;


    private static final int PAGE_SIZE = 3;
    private static final int MAX_PAGER = 4;
    private static final String ATTACH_PATH = "/upload/";

    @RequestMapping("/list")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String board(Model model, @RequestParam(value = "pNo", defaultValue = "1") int pNo) {

        PageRequest request = new PageRequest(pNo - 1, PAGE_SIZE, Sort.Direction.DESC, "regDate");
        Page<Board> boardList = boardService.getBoardList(request);
        Page<Board> result = boardRepository.findByStatus(1, request);

        model.addAttribute("boardList", boardList);
        model.addAttribute("totalPage", boardList.getTotalPages());
        model.addAttribute("totalElement", boardList.getTotalElements());
        model.addAttribute("pNo", pNo);
        model.addAttribute("pageSize", PAGE_SIZE);
        model.addAttribute("maxPager", MAX_PAGER);

        int totalBlock = (boardList.getTotalPages() - 1) / MAX_PAGER + 1;
        int currentBlock = (int) Math.ceil(pNo / (double) MAX_PAGER);

        int begin = 1;
        int end = boardList.getTotalPages();

        //게시글이 없을때
        if (boardList.getTotalElements() == 0) {
            end = 0;
        }

        //페이지가 많을때
        if (currentBlock == 1 && totalBlock > 1) {
            //첫 페이지
            begin = 1;
            end = MAX_PAGER;
        }

        if (totalBlock == currentBlock && totalBlock > 1) {
            //마지막 페이지
            begin = (currentBlock * MAX_PAGER) - (MAX_PAGER - 1);
            end = boardList.getTotalPages();
        }

        if (totalBlock > currentBlock && totalBlock > 1) {
            //중간 페이지
            begin = (currentBlock * MAX_PAGER) - (MAX_PAGER - 1);
            end = MAX_PAGER + (currentBlock - 1) * MAX_PAGER;
        }

        model.addAttribute("begin", begin);
        model.addAttribute("end", end);

        return "board";
    }


    @RequestMapping("/writeForm")
    public String boardWriteForm(Model model, Authentication auth) {
        model.addAttribute("auth", auth.getName());
        return "board/boardWrite";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String board(@PathVariable("id") Long id, Model model, Authentication auth) {

        boardService.increaseHit(id);

        Board board = boardService.getBoard(id);

        Image image;
        if (imageRepository.findByBoardBoardId(id) != null) {
            logger.error("boardId : " + id);
            image = imageRepository.findByBoardBoardId(id);
            model.addAttribute("image", image);

        }


        model.addAttribute("board", board);
        model.addAttribute("auth", auth.getName());


        return "board/detail";
    }


    @RequestMapping(value = "/modifyForm/{id}")
    public String modifyForm(@PathVariable("id") Long id, Model model) {

        Board board = boardService.getBoard(id);

        Image image;
        if (imageRepository.findByBoardBoardId(id) != null) {
            logger.error("boardId : " + id);
            image = imageRepository.findByBoardBoardId(id);
            model.addAttribute("image", image);

        }


        model.addAttribute("board", board);


        return "board/boardModify";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(MultipartHttpServletRequest multipartRequest, Board board) {
        logger.error(board.toString());


        MultipartFile mpf = multipartRequest.getFile("upload");

        if(mpf != null) {

            HttpSession session = multipartRequest.getSession();

            String root = session.getServletContext().getRealPath("/");

            String filePath = root + ATTACH_PATH;

            String genId = UUID.randomUUID().toString();

            String originalFilename = mpf.getOriginalFilename(); //파일명

            String exc = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());

            String fileFullPath = filePath + genId + "." + exc; //파일 전체 경로

            long fileSize = mpf.getSize();

            try {
                mpf.transferTo(new File(fileFullPath)); //파일저장

                Image image = new Image();

                image.setOriginName(originalFilename);
                image.setFileName(ATTACH_PATH + genId + "." + exc);
                image.setFileSize(fileSize);
                image.setBoard(board);

                boardService.fileUpdate(image);
            }
            catch (IOException e ) {
                e.printStackTrace();
            }

        }

        if (boardService.update(board)) {

            return "redirect:/board/list";
        } else {
            //수정해야함
            logger.error("error");
            return "redirect:/";
        }




    }


    @PostMapping(value = "/write")
    public String write(@Valid Board board, BindingResult bindingResult, MultipartHttpServletRequest multipartRequest, Authentication auth) {

        if(bindingResult.hasErrors()) {
            logger.info(" 유효성 에러 ");
            List<ObjectError> list = bindingResult.getAllErrors();
            for (ObjectError error : list) {
                logger.error("error:{}",error.getDefaultMessage());
            }

            return "/board/boardWrite";
        }



        User user = userService.getUserByUsername(auth.getName());
        boardService.create(board, user);



//        HttpSession session = multipartRequest.getSession();
//
//        String root = session.getServletContext().getRealPath("/");
//
//        String filePath = root + ATTACH_PATH;
//
//
//        File dir = new File(filePath);
//        if (!dir.isDirectory()) {
//            dir.mkdirs();
//        }
//
//        Iterator<String> itr = multipartRequest.getFileNames();
//
//
//        User user = userService.getUserByUsername(auth.getName());
//
//        boardService.create(board, user);
//
//
//        while (itr.hasNext()) {
//
//            MultipartFile mpf = multipartRequest.getFile(itr.next());
//
//            if (!mpf.isEmpty()) {
//
//
//                String genId = UUID.randomUUID().toString();
//
//                String originalFilename = mpf.getOriginalFilename(); //파일명
//
//                String exc = originalFilename.substring(
//                        originalFilename.lastIndexOf(".") + 1, originalFilename.length());
//
//                String fileFullPath = filePath + genId + "." + exc; //파일 전체 경로
//
//                long fileSize = mpf.getSize();
//
//
//                logger.error(fileFullPath);
//
//
//                try {
//                    mpf.transferTo(new File(fileFullPath)); //파일저장
//
//                    Image image = new Image();
//
//                    image.setOriginName(originalFilename);
//                    image.setFileName(ATTACH_PATH + genId + "." + exc);
//                    image.setFileSize(fileSize);
//                    image.setBoard(board);
//
//                    boardService.fileUpload(image);
//
//                    System.out.println("originalFilename => " + originalFilename);
//                    System.out.println("fileFullPath => " + fileFullPath);
//
//
//                } catch (Exception e) {
//                    System.out.println("postTempFile_ERROR======>" + fileFullPath);
//                    e.printStackTrace();
//
//                }
//
//            }
//
//        }

        return "redirect:/board/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {

        boardService.delete(id);


        return "redirect:/board/list";
    }

    @RequestMapping("/403")
    public String accessdeniedPage() {

        return "/error/403";
    }

}
