package com.zum.service;

import com.zum.domain.User;
import com.zum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public boolean create(User user) {

        if(userRepository.findOneByUserName(user.getUserName()) != null) {
            return false;
        }

        User regiUser = new User();

        regiUser.setUserName(user.getUserName());
        regiUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        regiUser.setEmail(user.getEmail());
        userRepository.save(regiUser);

        return true;
    }



}
