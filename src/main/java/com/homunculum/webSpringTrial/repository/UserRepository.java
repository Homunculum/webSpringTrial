package com.homunculum.webSpringTrial.repository;

import com.homunculum.webSpringTrial.module.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);
}
