package com.zum.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by joeylee on 2017-04-01.
 */

@Entity
@Table(name = "image")
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="file_id")
    private Long fileId;

    @Column(name = "origin_name", nullable = false, columnDefinition = "varchar(100)")
    private String originName;

    @Column(name = "file_name", nullable = false, columnDefinition = "varchar(100)")
    private String fileName;

    @Column(name = "file_size", nullable = false, columnDefinition = "int(50)")
    private Long fileSize;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    //즉시로딩 : 엔티티를 조회 할 때 연관된 엔티티도 함께 조회한다.
    @ManyToOne(targetEntity = Board.class, fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_image_to_board"))
    private Board board;


    public Image() {
    }

    public void insertImage(String originalFilename, String fileName, Long fileSize, Board board) {

        this.originName = originalFilename;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.board = board;

    }

    public void update(Image image) {
        this.originName = image.originName;
        this.fileSize = image.fileSize;
        this.fileName = image.fileName;

    }






}
