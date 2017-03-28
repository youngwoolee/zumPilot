package com.zum.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by joeylee on 2017-03-28.
 */
@Entity
@Table(name = "reply")
public class Reply {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="reply_id")
    private Long replyId;



    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_reply_writer"))
    private User writer;



    @ManyToOne(targetEntity = Board.class, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_reply_to_board"))
    private Board board;


    @Column(name="content")
    private String content;

    @Column(name = "reg_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    @Column(name="reply_group")
    private int group;

    //들여쓰기
    @Column(name="reply_depth")
    private int depth;

    //글 순서
    @Column(name="reply_thread")
    private int thread;



    public Reply() {
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



    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
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

    @Override
    public String toString() {
        return "Reply{" +
                "replyId=" + replyId +
                ", writer=" + writer +
                ", content='" + content + '\'' +
                ", regDate=" + regDate +
                ", group=" + group +
                ", depth=" + depth +
                ", thread=" + thread +
                '}';
    }
}
