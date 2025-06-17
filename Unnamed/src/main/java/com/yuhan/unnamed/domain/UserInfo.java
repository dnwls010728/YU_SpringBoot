package com.yuhan.unnamed.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserInfo {
    @Id
    @GeneratedValue
    private int id;

    private String username;
    private String password;
}
