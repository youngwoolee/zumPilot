package com.zum.domain;

import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Created by joeylee on 2017-03-21.
 */
public class SecurityUser extends org.springframework.security.core.userdetails.User{

    private static final long serialVersionUID = 7369103590269799780L;
    private User user;

    public SecurityUser(User user) {
        super(user.getUserName(), user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Role getRole() {
        return user.getRole();
    }

}
