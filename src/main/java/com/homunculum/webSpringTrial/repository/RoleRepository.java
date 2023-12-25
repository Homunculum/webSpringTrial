package com.homunculum.webSpringTrial.repository;

import com.homunculum.webSpringTrial.module.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
