package com.zum.service;

import com.zum.domain.Board;
import com.zum.domain.Image;
import com.zum.domain.User;
import com.zum.exception.NotFoundExceptionRest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by joeylee on 2017-03-22.
 */

public interface BoardService {

    Board getBoard(Long id);

    void create(Board board, User user, MultipartHttpServletRequest multipartRequest) throws IOException;

    void increaseHit(Long id);

    Page<Board> getBoardList(Pageable pageable);

    HashMap<String, Object> getPageInfo(Page<Board> boardList, int pNo);

    void delete(Long boardId);

    boolean update(Board board);

    void fileUpload(Image image);

    void fileUpdate(Image image);

    Image getImage(Long boardId);

//    Long getUserId(String username);

    Long getUserId(Long boardId);

    void modify(Long boardId, String title, String content, MultipartHttpServletRequest multipartRequest) throws IOException;


}
