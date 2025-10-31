package com.southdragon.book_ecommerce.dto;

import com.southdragon.book_ecommerce.type.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequest {
    private String fullName;
    private String email;
    private String phone;
    private GenderType gender;
    private LocalDateTime birthDate;
    private String avatarUrl;
}
