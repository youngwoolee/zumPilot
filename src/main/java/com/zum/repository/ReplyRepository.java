package com.zum.repository;

import com.zum.domain.Board;
import com.zum.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;



/**
 * Created by joeylee on 2017-03-28.
 */
public interface ReplyRepository extends JpaRepository<Reply, Long>{

    List<Reply> findByBoardId(Long boardId);
//
    // 주의. 컬럼명에 맞춰야함
    List<Reply> findByBoardIdOrderByThreadDesc(Long boardId);

//    @Query("select coalesce(max(r.thread),0) from Reply r where r.board = ?1")
//    int getMaxThread(Board board);

    @Query("select coalesce(max(r.thread),0) from Reply r where r.boardId = ?1")
    int getMaxThreadByBoardId(Long boardId);

    Reply findByReplyId(Long replyId);

    //이전 답글의 reply_thread
    @Query("select floor((r.thread-1)/1000) * 1000 from Reply r where r.replyId = ?1")
    int getPrevReplyThread(Long replyId);


    @Query("select r from Reply r where r.thread < ?1 and r.thread > ?2 and r.boardId = ?3")
    List<Reply> getReplyIdBetweenPrevCurrent(int thread, int prevThread, Long boardId);

    @Modifying
    @Transactional
    @Query("update Reply r set r.thread = r.thread - 1 where r.replyId = ?1")
    void updateReplyThread(Long replyId);

    Reply findOne(Long replyId);




}
