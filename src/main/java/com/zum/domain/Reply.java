package com.zum.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by joeylee on 2017-03-28.
 */
@Entity
@Table(name = "reply")
@Data
public class Reply{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="reply_id")
    private Long replyId;

    // 객체가 완전히 로드되기 전에 직렬화하려고하기 때문에 방지
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_reply_writer"))
    private User writer;

    @Column(name="board_boardId")
    private Long boardId;


    @Column(name="content", columnDefinition = "TEXT")
    @NotEmpty
    private String content;

    @Column(name = "reg_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    //들여쓰기
    @Column(name="reply_depth")
    private int depth;

    //글 순서
    @Column(name="reply_thread")
    private int thread;

    @Column(name = "status", nullable = false, insertable = false, columnDefinition = "INT(1) default 1")
    private int status;

    public Reply() {

        this.regDate = new Date();
        this.status = 1;

    }

    public Reply(User writer, String content) {
        this.writer = writer;
        this.content = content;
    }

    public void createReply(Long boardId, User user, int thread) {
      this.boardId = boardId;
      this.writer = user;
      this.thread = thread;

    }

    public void createAnswer(User user, Long boardId, int replyThread, int replyDepth ) {
        this.writer = user;
        this.boardId = boardId;
        this.thread = replyThread;
        this.depth = replyDepth;
    }

    public void updateStatus() {
        this.status = 0;
    }


}
