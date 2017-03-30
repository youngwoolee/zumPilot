package com.zum.repository;

import com.zum.domain.Board;
import com.zum.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;



/**
 * Created by joeylee on 2017-03-28.
 */
public interface ReplyRepository extends JpaRepository<Reply, Long>{

    List<Reply> findByBoardBoardId(Long boardId);

    // 주의. 컬럼명에 맞춰야함
    List<Reply> findByBoardBoardIdOrderByThreadDesc(Long boardId);

    @Query("select coalesce(max(r.thread),0) from Reply r where r.board = ?1")
    int getMaxThread(Board board);


}
