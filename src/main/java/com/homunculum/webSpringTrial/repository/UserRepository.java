package com.homunculum.webSpringTrial.repository;
/*JpaRepository sayesinde veritabanı ile iletişimin sağlanacağı bir interface yaratmış olacağız.
Bu interface’i extend ederek veritabanı entitylerimizin interfacelerini yazacağız.
UserRepository ve RoleRepositroy oluşturacağız ve bu iki class da JpaRepository’den extend olacak.*/

import com.homunculum.webSpringTrial.module.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {//JpaRepository save(), findById(), deleteById(), findAll() gibi yaygın metotları içerir.
    User findByUsername(String username);

    User findByEmail(String email);
}
