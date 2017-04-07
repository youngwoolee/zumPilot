package com.zum.repository;

import com.zum.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by joeylee on 2017-03-17.
 */

//User에 대한 repository pk는 Long 타입
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);
    User findByUserId(Long UserId);


}
