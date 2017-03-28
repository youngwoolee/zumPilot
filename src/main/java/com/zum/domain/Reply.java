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

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_reply_writer"))
    private User writer;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_reply_to_board"))
    private Board board;

    @Lob
    @Column(name="content")
    private String content;

    @Column(name = "reg_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

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

    @Override
    public String toString() {
        return "Reply{" +
                "replyId=" + replyId +
                ", writer=" + writer +
                ", content='" + content + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
