package com.yuhan.unnamed.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Board {
    @Id
    @GeneratedValue
    private int seq;

    private String title;

    @Column(updatable = false)
    private String writer;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String content;

    @Temporal(TemporalType.DATE)
    @Column(insertable = false, updatable = false,
            columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date createdDate;

    @Column(insertable = false, columnDefinition = "NUMBER DEFAULT 0")
    private int cnt;

    @Column(insertable = false, columnDefinition = "NUMBER DEFAULT 0")
    private int likeCnt;

    @Column(insertable = false, columnDefinition = "NUMBER DEFAULT 0")
    private int dislikeCnt;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
}
