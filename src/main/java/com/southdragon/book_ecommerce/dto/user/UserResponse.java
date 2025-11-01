package com.southdragon.book_ecommerce.dto.user;

import com.southdragon.book_ecommerce.type.GenderType;
import com.southdragon.book_ecommerce.type.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String username;

    private String passwordHash;

    private String email;

    private String fullName;

    private String phone;

    private GenderType gender;

    private LocalDateTime dateOfBirth;

    private String avatarUrl;

    private UserStatus userStatus;
}
