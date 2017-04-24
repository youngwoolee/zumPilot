package com.zum.service;

import com.zum.domain.Board;
import com.zum.domain.Image;
import com.zum.domain.User;
import com.zum.exception.NotFoundExceptionRest;
import com.zum.repository.BoardRepository;
import com.zum.repository.ImageRepository;
import com.zum.util.FileUploadUtil;
import com.zum.util.PageCustomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by joeylee on 2017-03-22.
 */
@Service
@Transactional
public class BoardServiceImpl implements BoardService {

   Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);


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
    public void modify(Long boardId, Board updateBoard, MultipartHttpServletRequest multipartRequest) throws IOException{


        Board board = boardRepository.findByBoardId(boardId);
        board.update(updateBoard);

        Image image = FileUploadUtil.saveMultipartFile(multipartRequest, board);
        if(image != null) {
            fileUpdate(image);
        }

    }

    @Override
    public Board getBoard(Long id) {

        Board board= boardRepository.findByBoardIdAndStatus(id);
        if(ObjectUtils.isEmpty(board)) {
            throw new NotFoundExceptionRest();
        }

        increaseHit(id);

        return board;
    }


    @Override
    public void increaseHit(Long id) {
        Board board = boardRepository.findOne(id);

        if(ObjectUtils.isEmpty(board)) {
            throw new NotFoundExceptionRest();
        }

        board.updateHit();
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
    public boolean update(Board board) {

        Board updateBoard = boardRepository.findOne(board.getBoardId());
        updateBoard.update(board);

        return true;
    }

    @Override
    public void fileUpload(Image image) {

        imageRepository.save(image);

    }

    @Override
    public void fileUpdate(Image image) {

        Image updateImage = imageRepository.findByBoardBoardId(image.getBoard().getBoardId());
        if(ObjectUtils.isEmpty(updateImage)) {
            imageRepository.save(image);
        }
        else{
            updateImage.update(image);
        }
    }

    @Override
    public void delete(Long boardId) {
        Board board = boardRepository.findOne(boardId);
        board.deleteBoard();
    }


}
