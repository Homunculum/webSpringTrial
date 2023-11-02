package com.homunculum.webSpringTrial.repository;

import com.homunculum.webSpringTrial.module.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String role);
}
