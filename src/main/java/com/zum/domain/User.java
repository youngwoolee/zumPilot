package com.zum.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
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

    @NotBlank(message = "이름을 작성해주세요.")
    @Column(name="username", nullable = false)
    private String userName;


    @NotBlank(message = "비밀번호를 작성해주세요.")
    @Column(name = "password", nullable = false)
    private String password;


    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;


    @NotBlank(message = "메일을 작성해주세요.")
    @Email(message = "메일의 양식을 지켜주세요.")
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
