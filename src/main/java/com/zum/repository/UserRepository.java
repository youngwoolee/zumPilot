package com.zum.repository;

import com.zum.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by joeylee on 2017-03-17.
 */

//User에 대한 repository pk는 Long 타입
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);
    String findOneByUserName(String username);


}
