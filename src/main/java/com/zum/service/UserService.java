package com.zum.service;

import com.zum.domain.User;

/**
 * Created by joeylee on 2017-03-21.
 */
public interface UserService {

    public User getUserByUsername(String username);
}