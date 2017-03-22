package com.zum.service;

import com.zum.domain.Board;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by joeylee on 2017-03-22.
 */

public interface BoardService {

    public List<Board> getBoardList();

//    public void create(Board board);
//
//    public void delete(Integer id);
//
//    public void update(Board board, Integer id);

}
