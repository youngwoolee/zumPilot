package com.zum.service;

import com.zum.domain.User;

/**
 * Created by joeylee on 2017-03-21.
 */

public interface UserService {

    User getUserByUserId(Long userId);
    User getUserByUsername(String username);
    void create(User user);
    void update(Long userId, User user);
    void leave(Long userId);

    boolean isExist(String username);





}
