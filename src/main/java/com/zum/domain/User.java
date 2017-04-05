package com.zum.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Created by joeylee on 2017-03-17.
 */
@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userid")
    private Long userId;



    @NotNull
    @Column(name="username")
    @Size(min=2, max=30)
    private String userName;


    @NotNull(message="패스워드를 입력해주세요")
    @Column(name = "password", columnDefinition = "varchar(60)", nullable = false)
    @Size(min=4, max=60, message = "비밀번호는 최소 4자이상 60자이하로 입력해주세요")
    private String password;


    @NotNull
    @Column(name = "role", columnDefinition = "varchar(30) default 'ROLE_USER'")
    @Enumerated(EnumType.STRING)
    private Role role;


    @NotNull(message="메일을 입력해주세요")
    @Email(message = "메일의 양식을 지켜주세요.")
    @Column(name = "email", columnDefinition = "varchar(30)")
    @Size(min=2, max=30)
    private String email;

    public User() {

        this.role = Role.ROLE_USER;
    }

    public User(String userName, String password, Role role, String email) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.email = email;
    }

}
