package com.links.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u.role FROM User u WHERE u.username = :username")
    String findRoleByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.username = :username")
    void deleteByUsername(@Param("username") String username);

    @Query("SELECT u.suspended FROM User u WHERE u.username = :username")
    Boolean isUserSuspended(@Param("username") String username);

    @Query("SELECT u.maxScore FROM User u WHERE u.username = :username")
    float findMaxScoreByUsername(@Param("username") String username);

    @Query("SELECT u.bestTime FROM User u WHERE u.username = :username")
    int findBestTimeByUsername(String username);
}
