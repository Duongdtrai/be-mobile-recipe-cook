package com.alibou.spring_security.modules.user.repositories;

import com.alibou.spring_security.modules.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * from users where email = :email AND status = true", nativeQuery = true)
    Optional<User> findByEmail(String email);

    User findByEmailAndStatus(String email, Boolean status);
}
