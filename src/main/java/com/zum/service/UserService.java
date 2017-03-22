package com.zum.service;

import com.zum.domain.User;
import org.springframework.stereotype.Service;

/**
 * Created by joeylee on 2017-03-21.
 */

public interface UserService {

    public User getUserByUsername(String username);
    public boolean create(User user);


}
