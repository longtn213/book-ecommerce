package com.southdragon.book_ecommerce.controller;

import com.southdragon.book_ecommerce.dto.ChangePasswordRequest;
import com.southdragon.book_ecommerce.dto.UpdateUserRequest;
import com.southdragon.book_ecommerce.dto.UserRequest;
import com.southdragon.book_ecommerce.dto.UserResponse;
import com.southdragon.book_ecommerce.dto.base.ApiResponse;
import com.southdragon.book_ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.southdragon.book_ecommerce.constant.MessageConstant.USER_PROFILE_SUCCESS;
import static com.southdragon.book_ecommerce.constant.MessageConstant.USER_UPDATE_SUCCESS;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ApiResponse<UserResponse> getProfileUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserResponse response =  userService.getProfileUser(username);
        return ApiResponse.success(USER_PROFILE_SUCCESS,response);
    }
    @PutMapping("/update")
    public String updateUser( @RequestBody UpdateUserRequest request) {
        userService.updateUser(request);
        return "Cập nhật thông tin thành công";
    }
    @PutMapping("/change-password")
    public ApiResponse<Void> changePassword( @RequestBody ChangePasswordRequest request) {
        userService.changePassword(request);
        return ApiResponse.error(USER_UPDATE_SUCCESS);
    }
}
