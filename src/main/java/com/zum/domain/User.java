package com.zum.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;


/**
 * Created by joeylee on 2017-03-17.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userid")
    private Long userId;

    @Column(name="username", nullable = false)
    private String userName;

//    @Size(min = 1, max = 10, message = "비밀번호는 1~10자로 제한됨.")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "enabled", nullable = false)
    private int enabled;


    public User() {

        this.enabled = 1;
        this.role = Role.ROLE_USER;

    }

    public User(String userName, String password, Role role, String email, int enabled) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.email = email;
        this.enabled = enabled;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

//    public void update(User user){
//        this.userId = user.userId;
//    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                '}';
    }


}
