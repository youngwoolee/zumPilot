package com.zum.repository;

import com.zum.domain.Board;
import com.zum.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by joeylee on 2017-03-22.
 */


public interface BoardRepository extends JpaRepository<Board, Long>{

    Page<Board> findByStatus(int status, Pageable pageable);
    Page<Board> findAll(Pageable pageable);

    Board findByBoardId(Long boardId);

    @Query("select b from Board b where b.boardId = ?1 and b.status=1")
    Board findByBoardIdAndStatus(Long boardId);



}
