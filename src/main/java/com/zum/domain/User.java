package com.zum.domain;

import com.zum.exception.UserLeaveException;
import com.zum.util.BcryptPasswordUtil;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
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

    @NotEmpty
    @Column(name="username")
    @Size(min=2, max=30)
    private String userName;


    @NotEmpty
    @Column(name = "password", columnDefinition = "varchar(60)", nullable = false)
    private String password;



    @Column(name = "role", columnDefinition = "varchar(30) default 'ROLE_USER'")
    @Enumerated(EnumType.STRING)
    private Role role;


    @NotEmpty
    @Email
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

    public void passwordEncode(){
        this.password = BcryptPasswordUtil.bCryptPasswordEncoder.encode(this.password);
    }

    public void updateUserInfo(User user) {
        if( (password != null) || !("".equals(user.password))) {
            this.password = BcryptPasswordUtil.bCryptPasswordEncoder.encode(user.password);
        }
        this.email = user.email;
    }

    public void leaveUser() {
        this.role = Role.ROLE_LEAVE;
    }

    public void isLeave() {
        if(this.role == Role.ROLE_LEAVE) {
            throw new UserLeaveException();
        }
    }

}
