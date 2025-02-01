package com.usersapi.usersapi.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{ //Integer because the primary key is int
    void deleteById(Integer id);

    Optional<User> findById(Integer id);
}
