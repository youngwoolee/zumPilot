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

    @Override
    public User getUserByUserId(Long userId) {
        return userRepository.findByUserId(userId);
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

    @Transactional
    public boolean update(User user) {

        User updateUser = userRepository.findByUserName(user.getUserName());

        updateUser.setUserName(user.getUserName());
        updateUser.setEmail(user.getEmail());

        if(user.getPassword() != null) {
            //비밀번호 바꿈
            updateUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }

        userRepository.save(updateUser);

        return true;
    }

    @Transactional
    public void leave(Long userId) {


        User user = userRepository.findByUserId(userId);


        user.setEnabled(0);


        userRepository.save(user);

    }







}
