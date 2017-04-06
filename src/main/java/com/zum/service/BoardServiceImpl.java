package com.zum.service;

import com.zum.domain.Board;
import com.zum.domain.Image;
import com.zum.domain.User;
import com.zum.exception.NotFoundExceptionRest;
import com.zum.repository.BoardRepository;
import com.zum.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

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
    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    @Override
    public void create(Board board, User user) {
        board.setUserId(user);
        boardRepository.save(board);
    }

    @Override
    public Board getBoard(Long id) {
        return boardRepository.findOne(id);
    }

    @Override
    public void increaseHit(Long id) throws NotFoundExceptionRest {
        Board board = boardRepository.findOne(id);

        if(board == null) {
            throw new NotFoundExceptionRest();
        }

        board.setHit(board.getHit()+1);

        boardRepository.save(board);
    }

    @Override
    public Page<Board> getBoardList(Pageable pageable) {
        return boardRepository.findByStatus(1, pageable);
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

        updateImage.setOriginName(image.getOriginName());
        updateImage.setFileSize(image.getFileSize());
        updateImage.setFileName(image.getFileName());

        imageRepository.save(updateImage);
    }

    @Override
    public void delete(Long boardId) {
        Board board = boardRepository.findOne(boardId);

        board.setStatus(0);

        boardRepository.save(board);



    }
//
//    @Override
//    public void update(Board board, Integer id) {
//
//    }
}
