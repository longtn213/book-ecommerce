package com.southdragon.book_ecommerce.service;

import com.southdragon.book_ecommerce.dto.ChangePasswordRequest;
import com.southdragon.book_ecommerce.dto.UpdateUserRequest;
import com.southdragon.book_ecommerce.dto.UserRequest;
import com.southdragon.book_ecommerce.dto.UserResponse;
import com.southdragon.book_ecommerce.dto.base.ApiResponse;
import com.southdragon.book_ecommerce.model.User;
import com.southdragon.book_ecommerce.repository.UserRepository;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.southdragon.book_ecommerce.constant.MessageConstant.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserResponse getProfileUser(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            throw new RuntimeException(USER_NOT_FOUND);
        }
        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .avatarUrl(user.getAvatarUrl())
                .dateOfBirth(user.getDateOfBirth())
                .fullName(user.getFullName())
                .gender(user.getGender())
                .userStatus(user.getUserStatus())
                .build();
    }

    public void updateUser(UpdateUserRequest request) {
    }

    public void changePassword(ChangePasswordRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            throw new RuntimeException(USER_NOT_FOUND);
        }

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPasswordHash())) {
            throw new RuntimeException(PASSWORD_NOT_CORRECT);
        }
        user.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}
