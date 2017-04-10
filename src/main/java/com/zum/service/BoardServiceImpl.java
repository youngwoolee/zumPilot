package com.zum.service;

import com.zum.domain.Board;
import com.zum.domain.Image;
import com.zum.domain.User;
import com.zum.exception.NotFoundExceptionRest;
import com.zum.repository.BoardRepository;
import com.zum.repository.ImageRepository;
import com.zum.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by joeylee on 2017-03-22.
 */
@Service
@Transactional
public class BoardServiceImpl implements BoardService {

   Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
    private static final int PAGE_SIZE = 3;
    private static final int MAX_PAGER = 4;


   @Autowired
   private BoardRepository boardRepository;
   @Autowired
   private ImageRepository imageRepository;


    @Override
    public void create(Board board, User user, MultipartHttpServletRequest multipartRequest) throws IOException {

        board.setUserId(user);
        boardRepository.save(board);

        Image image = FileUploadUtil.saveMultipartFile(multipartRequest, board);
        if(image != null) {
            fileUpload(image);
        }
    }

    @Override
    public void modify(Long boardId, String title, String content, MultipartHttpServletRequest multipartRequest) throws IOException{

        Board board = boardRepository.findByBoardId(boardId);
        board.modifyBoard(title, content);

        Image image = FileUploadUtil.saveMultipartFile(multipartRequest, board);
        if(image != null) {
            fileUpdate(image);
        }

    }

    @Override
    public Board getBoard(Long id) {

        increaseHit(id);

        return boardRepository.findOne(id);
    }


    @Override
    public void increaseHit(Long id) {
        Board board = boardRepository.findOne(id);

        if(board == null) {
            throw new NotFoundExceptionRest();
        }

        board.updateHit();
        boardRepository.save(board);
    }

    @Override
    public Image getImage(Long boardId) {

        Image image = imageRepository.findByBoardBoardId(boardId);
        if(image == null) {
            return null;
        }

        return image;
    }

    @Override
    public Long getUserId(Long boardId) {
        Board board = boardRepository.findByBoardId(boardId);

        return board.getUserId().getUserId();
    }


    @Override
    public Page<Board> getBoardList(Pageable pageable) {

       return boardRepository.findByStatus(1, pageable);
    }


    @Override
    public HashMap<String, Object> getPageInfo(Page<Board> boardList, int pNo) {

        return PageCustomUtil.getPageInfo(boardList, PAGE_SIZE, MAX_PAGER, pNo);

    }

    @Override
    public boolean update(Board board) {


        Board updateBoard = boardRepository.findOne(board.getBoardId());

        logger.error(updateBoard.toString());
        updateBoard.update(board);
        updateBoard.setTitle(board.getTitle());
        updateBoard.setContent(board.getContent());

        boardRepository.save(updateBoard);

        return true;
    }

    @Override
    public void fileUpload(Image image) {

        imageRepository.save(image);

    }

    @Override
    public void fileUpdate(Image image) {

        Image updateImage = imageRepository.findByBoardBoardId(image.getBoard().getBoardId());
        updateImage.update(image);
        imageRepository.save(updateImage);
    }

    @Override
    public void delete(Long boardId) {
        Board board = boardRepository.findOne(boardId);
        board.deleteBoard();
        boardRepository.save(board);

    }


}
