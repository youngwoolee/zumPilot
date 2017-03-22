package com.zum.repository;

import com.zum.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by joeylee on 2017-03-17.
 */


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);
    String findOneByUserName(String username);


}
