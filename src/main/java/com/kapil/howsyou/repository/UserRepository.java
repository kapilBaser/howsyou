package com.kapil.howsyou.repository;

import com.kapil.howsyou.entity.HowsyouUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<HowsyouUser, Long> {

    Optional<HowsyouUser> findByEmail(String email);
}
