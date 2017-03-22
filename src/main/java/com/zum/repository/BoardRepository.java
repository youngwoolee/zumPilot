package com.zum.repository;

import com.zum.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by joeylee on 2017-03-22.
 */


public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByBoard();
}
