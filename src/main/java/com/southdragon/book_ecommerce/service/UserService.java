package com.southdragon.book_ecommerce.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.southdragon.book_ecommerce.dto.user.*;
import com.southdragon.book_ecommerce.model.Order;
import com.southdragon.book_ecommerce.model.User;
import com.southdragon.book_ecommerce.repository.OrderRepository;
import com.southdragon.book_ecommerce.repository.UserRepository;
import com.southdragon.book_ecommerce.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.southdragon.book_ecommerce.constant.MessageConstant.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final OrderRepository orderRepository;

    public UserResponse getProfileUser() {
        User user = jwtUtils.getCurrentUser();
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


    public void changePassword(ChangePasswordRequest request) {
        User user = jwtUtils.getCurrentUser();

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPasswordHash())) {
            throw new RuntimeException(PASSWORD_NOT_CORRECT);
        }
        user.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    public String uploadAvatar(MultipartFile file) {
        User user = jwtUtils.getCurrentUser();
        String urlAvatar = jwtUtils.uploadImage(file);
        user.setAvatarUrl(urlAvatar);
        userRepository.save(user);
        return urlAvatar;
    }

    public UserProfileDto updateProfile(UserUpdateRequest request) {
        User user = jwtUtils.getCurrentUser();

        // Cập nhật chỉ khi có giá trị mới và khác với giá trị cũ
        if (request.getFullName() != null && !request.getFullName().equals(user.getFullName())) {
            user.setFullName(request.getFullName());
        }

        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            user.setEmail(request.getEmail());
        }

        if (request.getPhone() != null && !request.getPhone().equals(user.getPhone())) {
            user.setPhone(request.getPhone());
        }

        if (request.getGender() != null && !request.getGender().equals(user.getGender())) {
            user.setGender(request.getGender());
        }

        if (request.getBirthDate() != null && !request.getBirthDate().equals(user.getDateOfBirth())) {
            user.setDateOfBirth(request.getBirthDate());
        }

        if (request.getAvatarUrl() != null && !request.getAvatarUrl().equals(user.getAvatarUrl())) {
            user.setAvatarUrl(request.getAvatarUrl());
        }

        // Lưu thay đổi nếu có
        User updatedUser = userRepository.save(user);
        return UserProfileDto.fromEntity(updatedUser);
    }

    public List<OrderUserSummaryDto> getUserOrders() {
        User user = jwtUtils.getCurrentUser();
        List<Order> orderList = orderRepository.getOrderByUserId(user.getId());

        return orderList.stream().map(OrderUserSummaryDto::fromEntity).toList();
    }

}
