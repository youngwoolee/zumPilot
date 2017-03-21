package com.zum.service;

import com.zum.domain.User;
import com.zum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by joeylee on 2017-03-21.
 */
@Service
public class UserServcieImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServcieImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUserName(username);
    }
}
