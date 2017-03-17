package com.zum.domain;

import javax.persistence.*;

/**
 * Created by joeylee on 2017-03-17.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "no")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long no;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    public User(String userId, String password, String email) {
        this.userId = userId;
        this.password = password;
        this.email = email;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
