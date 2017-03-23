package com.zum.service;

import com.zum.domain.Board;
import com.zum.domain.User;
import com.zum.repository.BoardRepository;
import com.zum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by joeylee on 2017-03-22.
 */
@Service
public class BoardServiceImpl implements BoardService {


    private final BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

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

//
//    @Override
//    public void delete(Integer id) {
//
//    }
//
//    @Override
//    public void update(Board board, Integer id) {
//
//    }
}
