package com.zum.service;

import com.zum.domain.Role;
import com.zum.domain.User;
import com.zum.exception.AuthenticationException;
import com.zum.exception.UserDuplicationException;
import com.zum.exception.UserLeaveException;
import com.zum.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
        User user = userRepository.findByUserName(username);
        user.isLeave();
        return user;
    }

    @Override
    public User getUserByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }

    public void create(User regiUser) {

        if(!ObjectUtils.isEmpty(userRepository.findByUserName(regiUser.getUserName()))) {
            throw new UserDuplicationException();
        }
        regiUser.passwordEncode();
        userRepository.save(regiUser);
    }

    @Override
    public void update(Long userId, User updateUser) {

        User user = userRepository.findByUserId(userId);
        user.updateUserInfo(updateUser);
    }

    public void leave(Long userId) {
        User user = userRepository.findByUserId(userId);
        user.leaveUser();
    }

    @Override
    public boolean isExist(String username) {

        User user = userRepository.findByUserName(username);

        if(ObjectUtils.isEmpty(user)) {
            return false;
        }

        return true;
    }
}
