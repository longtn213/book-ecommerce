package com.southdragon.book_ecommerce.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.southdragon.book_ecommerce.dto.ChangePasswordRequest;
import com.southdragon.book_ecommerce.dto.UserProfileDto;
import com.southdragon.book_ecommerce.dto.UserResponse;
import com.southdragon.book_ecommerce.dto.UserUpdateRequest;
import com.southdragon.book_ecommerce.model.User;
import com.southdragon.book_ecommerce.repository.UserRepository;
import com.southdragon.book_ecommerce.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.southdragon.book_ecommerce.constant.MessageConstant.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final Cloudinary cloudinary;

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
        try {
            var uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", "bookstore/avatar",
                            "resource_type", "auto"
                    )
            );

            String url = uploadResult.get("secure_url").toString();

            User user = jwtUtils.getCurrentUser();
            user.setAvatarUrl(url);
            userRepository.save(user);
            return url;
        } catch (Exception e) {
            throw new RuntimeException("Upload to Cloudinary failed", e);
        }
    }

    public UserProfileDto updateProfile(UserUpdateRequest request) {
        User user = jwtUtils.getCurrentUser();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setGender(request.getGender());
        user.setDateOfBirth(request.getBirthDate());
        user.setAvatarUrl(request.getAvatarUrl());
        User updatedUser =  userRepository.save(user);
        return UserProfileDto.fromEntity(updatedUser);
    }
}
