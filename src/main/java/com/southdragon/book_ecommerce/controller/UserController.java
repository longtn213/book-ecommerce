package com.southdragon.book_ecommerce.controller;

import com.southdragon.book_ecommerce.dto.UpdateUserRequest;
import com.southdragon.book_ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String getProfileUser(@RequestBody UpdateUserRequest request) {
        userService.getProfileUser(request);
        return "Cập nhật thông tin thành công";
    }
    @PutMapping("/update")
    public String updateUser( @RequestBody UpdateUserRequest request) {
        userService.updateUser(request);
        return "Cập nhật thông tin thành công";
    }
}
