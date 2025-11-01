package com.southdragon.book_ecommerce.dto.user;

import com.southdragon.book_ecommerce.model.User;
import com.southdragon.book_ecommerce.type.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {
    private String fullName;
    private String email;
    private String phone;
    private GenderType gender;
    private LocalDateTime birthDate;
    private String avatarUrl;

    public static UserProfileDto fromEntity(User user) {
        return UserProfileDto.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .gender(user.getGender())
                .birthDate(user.getDateOfBirth())
                .avatarUrl(user.getAvatarUrl())
                .build();
    }
}

