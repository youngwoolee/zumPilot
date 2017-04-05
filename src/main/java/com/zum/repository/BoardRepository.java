package com.zum.repository;

import com.zum.domain.Board;
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


}
