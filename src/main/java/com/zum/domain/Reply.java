package com.zum.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.omg.CORBA.ServerRequest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by joeylee on 2017-03-28.
 */
@Entity
@Table(name = "reply")
public class Reply{

//    private static final long serialVersionUID = 4278014816235716721L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="reply_id")
    private Long replyId;



    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_reply_writer"))
    private User writer;


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(targetEntity = Board.class, fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_reply_to_board"))
    private Board board;


    @Column(name="content")
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

    @Column(name = "status", nullable = false, insertable = false, columnDefinition = "int default 1")
    private int status;


    public Reply() {

        this.regDate = new Date();
        this.status = 1;

    }

    public Reply(User writer, String content) {
        this.writer = writer;
        this.content = content;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getThread() {
        return thread;
    }

    public void setThread(int thread) {
        this.thread = thread;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyId=" + replyId +
                ", writer=" + writer +
                ", board=" + board +
                ", content='" + content + '\'' +
                ", regDate=" + regDate +
                ", depth=" + depth +
                ", thread=" + thread +
                ", status=" + status +
                '}';
    }
}
