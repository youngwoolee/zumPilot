package com.zum.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by joeylee on 2017-03-22.
 */
@Entity
@Table(name = "board")
@Data
public class Board {

    public Board() {

        this.status = 1;
        this.regDate = new Date();
        this.hit = 0;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="board_id")
    private Long boardId;

    @Length(min=2, max=30)
    @Column(name = "title", nullable = false, columnDefinition = "varchar(40)")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    @NotEmpty
    private String content;

    @Column(name = "reg_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    @Column(name = "hit", nullable = false)
    private int hit;

    @Column(name = "status", nullable = false, insertable = false, columnDefinition = "INT(1) default 1")
    private int status;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_board_to_user"))
    private User userId;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="board_boardId", foreignKey = @ForeignKey(name = "fk_board_reply"))
    private List<Reply> reply;

    public void updateHit() {
        this.hit++;
    }

    public void update(Board board) {
        this.title = board.title;
        this.content = board.content;
    }

    public void deleteBoard() {
        this.status = 0;
    }


}
