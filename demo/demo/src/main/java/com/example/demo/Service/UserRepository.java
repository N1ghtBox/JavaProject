package com.example.demo.Service;

import com.example.demo.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    User findByEmailIgnoreCase(String emailId);

    User findUserById(Long id);
}
