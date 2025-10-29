package com.southdragon.book_ecommerce.service;

import com.southdragon.book_ecommerce.dto.UpdateUserRequest;
import com.southdragon.book_ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public void getProfileUser(UpdateUserRequest request) {
    }

    public void updateUser(UpdateUserRequest request) {
    }
}
