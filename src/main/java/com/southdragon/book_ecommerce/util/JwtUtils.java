package com.southdragon.book_ecommerce.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.southdragon.book_ecommerce.model.User;
import com.southdragon.book_ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import static com.southdragon.book_ecommerce.constant.MessageConstant.USER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    private final UserRepository userRepository;
    private final Cloudinary cloudinary;


    public User getCurrentUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));
    }

    public String uploadImage(MultipartFile file) {
        try {
            var uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", "bookstore/avatar",
                            "resource_type", "auto"
                    )
            );

            return uploadResult.get("secure_url").toString();
        } catch (Exception e) {
            throw new RuntimeException("Upload to Cloudinary failed", e);
        }
    }
}
