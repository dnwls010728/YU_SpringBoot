package com.yuhan.unnamed.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

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
}
