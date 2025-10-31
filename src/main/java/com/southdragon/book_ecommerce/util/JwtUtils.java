package com.southdragon.book_ecommerce.util;

import com.southdragon.book_ecommerce.model.User;
import com.southdragon.book_ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static com.southdragon.book_ecommerce.constant.MessageConstant.USER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    private final UserRepository userRepository;

    public User getCurrentUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));
    }
}
