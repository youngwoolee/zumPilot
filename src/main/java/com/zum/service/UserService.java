package com.zum.service;

import com.zum.domain.User;

/**
 * Created by joeylee on 2017-03-21.
 */

public interface UserService {

    User getUserByUsername(String username);
    void create(User user);
    void update(Long userId, String password, String email);
    void leave(Long userId);
    void isAuthenticated(String username, Long userId);





}
