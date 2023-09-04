package com.example.financetest.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    List<User> findByIdNot(int id);

    Optional<User> findByAccessToken(String accessToken);

    boolean existsByUsername(String username);

}
