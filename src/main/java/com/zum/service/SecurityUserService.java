package com.zum.service;

import com.zum.domain.Role;
import com.zum.domain.SecurityUser;
import com.zum.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(SecurityUserService.class);

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getUserByUsername(username);

        return new SecurityUser(user);
    }


}
