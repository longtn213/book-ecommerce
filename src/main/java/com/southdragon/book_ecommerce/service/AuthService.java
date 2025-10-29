package com.southdragon.book_ecommerce.service;

import com.southdragon.book_ecommerce.dto.*;
import com.southdragon.book_ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {
    }

    public String forgotPassword(ForgotPasswordRequest request) {
    }

    public AuthResponse login(LoginRequest request) {
    }

    public void updateUser(Long userId, UpdateUserRequest request) {
    }
}

