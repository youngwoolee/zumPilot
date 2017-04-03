package com.zum.service;

import com.zum.domain.Board;
import com.zum.domain.Image;
import com.zum.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by joeylee on 2017-03-22.
 */

public interface BoardService {

    public List<Board> getBoardList();

    public Board getBoard(Long id);

    public void create(Board board, User user);

    public void increaseHit(Long id);

    public Page<Board> getBoardList(Pageable pageable);

    public void delete(Long boardId);
//
    public boolean update(Board board);

    public void fileUpload(Image image);

    public void fileUpdate(Image image);


}
