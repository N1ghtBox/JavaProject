package com.example.demo.Website.Security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository
        extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
