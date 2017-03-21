package com.zum.service;

import com.zum.domain.SecurityUser;
import com.zum.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by joeylee on 2017-03-21.
 */
@Service
public class SecurityUserService implements UserDetailsService{

    private final UserService userService;

    @Autowired
    public SecurityUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        //만든 유저를 유저디테일로 만든다
        return new SecurityUser(user);
    }
}
