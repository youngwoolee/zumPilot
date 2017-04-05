package com.zum.service;

import com.zum.domain.Role;
import com.zum.domain.User;
import com.zum.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by joeylee on 2017-03-21.
 */
@Service
@Transactional
public class UserServcieImpl implements UserService {


    Logger logger = LoggerFactory.getLogger(UserServcieImpl.class);

    @Autowired
    private UserRepository userRepository;




    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public User getUserByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }


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


    public boolean update(User user) {

        User updateUser = userRepository.findByUserName(user.getUserName());

        updateUser.setUserName(user.getUserName());
        updateUser.setEmail(user.getEmail());


        if(user.getPassword() != null && !"".equals(user.getPassword())) {

            //비밀번호 바꿈
            updateUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }

        userRepository.save(updateUser);

        return true;
    }


    public void leave(Long userId) {


        User user = userRepository.findByUserId(userId);

        user.setRole(Role.ROLE_LEAVE);


        userRepository.save(user);

    }







}
