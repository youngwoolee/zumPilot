package com.zum.service;

import com.zum.domain.Board;
import com.zum.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by joeylee on 2017-03-22.
 */

public interface BoardService {

    public List<Board> getBoardList();

    public Board getBoard(Long id);

    public void create(Board board, User user);

//    public void delete(Integer id);
//
//    public void update(Board board, Integer id);

}
