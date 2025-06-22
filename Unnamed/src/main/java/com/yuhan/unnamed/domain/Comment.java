package com.yuhan.unnamed.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private int seq;

    private int boardSeq;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "boardSeq",
            referencedColumnName = "seq",
            insertable = false, updatable = false)
    private Board board;

    @Column(updatable = false)
    private String writer;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String content;

    @Temporal(TemporalType.DATE)
    @Column(insertable = false, updatable = false,
            columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date createdDate;
}
