package com.zum.repository;

import com.zum.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by joeylee on 2017-03-17.
 */
public interface UserDao extends JpaRepository<User, Integer> {

}
