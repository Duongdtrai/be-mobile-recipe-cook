package com.alibou.spring_security.modules.user.repositories;

import com.alibou.spring_security.modules.user.entities.Token;
import com.alibou.spring_security.modules.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TokenRepository extends JpaRepository<Token, Integer> {
    Integer countByToken(String token);

    void deleteByUserIdAndToken(Integer userId, String token);
}
