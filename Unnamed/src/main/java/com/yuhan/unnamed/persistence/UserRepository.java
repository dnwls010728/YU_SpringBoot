package com.yuhan.unnamed.persistence;

import com.yuhan.unnamed.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {
    UserInfo findByUsername(String username);
}
