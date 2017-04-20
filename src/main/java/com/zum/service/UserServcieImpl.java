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
        if(user.getRole() == Role.ROLE_LEAVE) {
            //탈퇴한 사용자입니다.
            throw new UserLeaveException();
        }
        return user;
    }
    
    public void create(User regiUser) {

        if(userRepository.findByUserName(regiUser.getUserName()) != null) {
            throw new UserDuplicationException();
        }
        regiUser.passwordEncode();
        userRepository.save(regiUser);
    }

    @Override
    public void update(Long userId, String password, String email) {

        User user = userRepository.findByUserId(userId);
        user.updateUserInfo(password, email);
    }

    @Override
    public void isAuthenticated(String username, Long userId) {
        if(!userRepository.findByUserId(userId).getUserName().equals(username)) {
            throw new AuthenticationException();
        }
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
