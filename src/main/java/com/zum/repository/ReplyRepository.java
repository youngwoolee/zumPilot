package com.zum.repository;

import com.zum.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;



/**
 * Created by joeylee on 2017-03-28.
 */
public interface ReplyRepository extends JpaRepository<Reply, Long>{

//    @Query("select r from Reply r WHERE r.BOARD_BOARD_ID = ?1 ORDER BY r.REPLY_THREAD DESC")
    List<Reply> findAllByBoardBoardId(Long boardId);

//    List<Reply> findAllByBoardBoardId(Long boardId);
//    List<Reply> findAllOrderByReplyIdDesc();

//    List<Reply> findByBoardBoardIdOrderByReplyThreadDesc(Long boardId);


}
