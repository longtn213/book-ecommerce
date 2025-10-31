package com.southdragon.book_ecommerce.controller;

import com.southdragon.book_ecommerce.dto.*;
import com.southdragon.book_ecommerce.dto.base.ApiResponse;
import com.southdragon.book_ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.southdragon.book_ecommerce.constant.MessageConstant.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<UserResponse>> getProfileUser() {
        UserResponse response =  userService.getProfileUser();
        return ResponseEntity.ok(ApiResponse.success(USER_PROFILE_SUCCESS,response));
    }
    @PutMapping("/change-password")
    public ResponseEntity<ApiResponse<Void>> changePassword(@RequestBody ChangePasswordRequest request) {
        userService.changePassword(request);
        return ResponseEntity.ok(ApiResponse.success(USER_UPDATE_SUCCESS));
    }
    // Cập nhật thông tin cá nhân
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<UserProfileDto>> updateProfile(@RequestBody UserUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(USER_UPDATE_SUCCESS,userService.updateProfile(request)));
    }

    // Upload avatar
    @PostMapping("/avatar")
    public ResponseEntity<ApiResponse<String>> uploadAvatar(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(ApiResponse.success(COMMON_UPLOAD_SUCCESS,userService.uploadAvatar(file)));
    }

//    // Danh sách đơn hàng
//    @GetMapping("/orders")
//    public ResponseEntity<ApiResponse<List<OrderSummaryDto>>> getOrders() {
//        return ResponseEntity.ok(ApiResponse.success(userService.getUserOrders()));
//    }
//
//    // Wishlist
//    @GetMapping("/wishlist")
//    public ResponseEntity<ApiResponse<List<WishlistItemDto>>> getWishlist() {
//        return ResponseEntity.ok(ApiResponse.success(userService.getWishlist()));
//    }
//
//    @PostMapping("/wishlist/{bookId}")
//    public ResponseEntity<ApiResponse<String>> toggleWishlist(@PathVariable Long bookId) {
//        return ResponseEntity.ok(ApiResponse.success(userService.toggleWishlist(bookId)));
//    }
//
//    // Notifications
//    @GetMapping("/notifications")
//    public ResponseEntity<ApiResponse<List<NotificationDto>>> getNotifications() {
//        return ResponseEntity.ok(ApiResponse.success(userService.getNotifications()));
//    }
}
