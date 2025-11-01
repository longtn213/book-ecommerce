package com.southdragon.book_ecommerce.service;

import com.southdragon.book_ecommerce.config.JwtService;
import com.southdragon.book_ecommerce.dto.auth.*;
import com.southdragon.book_ecommerce.dto.base.ApiResponse;
import com.southdragon.book_ecommerce.model.User;
import com.southdragon.book_ecommerce.repository.UserRepository;
import com.southdragon.book_ecommerce.service.config.MailService;
import com.southdragon.book_ecommerce.type.RoleType;
import com.southdragon.book_ecommerce.type.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.southdragon.book_ecommerce.constant.MessageConstant.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final MailService mailService;

    @Value("${app.reset-password.url}")
    private String resetPasswordUrl;

    public ApiResponse<AuthResponse> register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername()) || userRepository.existsByEmail(request.getEmail())) {
            return ApiResponse.error(AUTH_REGISTER_DUPLICATE);
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setUserStatus(UserStatus.ACTIVE);
        user.setRole(RoleType.CUSTOMER);
        userRepository.save(user);
        String token = jwtService.generateToken(user.getUsername(), user.getRole().name());
        AuthResponse response =  new AuthResponse(token, user.getUsername(), user.getEmail());
        return ApiResponse.success(AUTH_REGISTER_SUCCESS, response);
    }

    public ApiResponse<AuthResponse> login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElse(null);
        if (user == null) {
            return ApiResponse.error(AUTH_USER_NOT_FOUND);
        }

        if(!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            return ApiResponse.error(AUTH_LOGIN_FAILED);
        }
        String token = jwtService.generateToken(user.getUsername(), user.getRole().name());
        AuthResponse response =  new AuthResponse(token, user.getUsername(), user.getEmail());
        return ApiResponse.success(AUTH_LOGIN_SUCCESS, response);
    }

    // ✅ Quên mật khẩu: gửi email reset
    public ApiResponse<Void> forgotPassword(ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);

        if (user == null) {
            return ApiResponse.error(AUTH_EMAIL_NOT_FOUND);
        }

        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setResetTokenExpiry(LocalDateTime.now().plusMinutes(5)); // token hết hạn sau 5 phút
        userRepository.save(user);

        String link = resetPasswordUrl + token;
        mailService.sendMail(
                user.getEmail(),
                "Đặt lại mật khẩu",
                "Chào " + user.getUsername() + ",\n\n" +
                        "Hãy click vào link sau để đặt lại mật khẩu của bạn:\n" + link + "\n\n" +
                        "Link này sẽ hết hạn sau 5 phút."
        );

        return ApiResponse.success(PASSWORD_RESET_EMAIL_SENT + user.getEmail());
    }
    // ✅ Cập nhật mật khẩu mới
    public ApiResponse<Void> resetPassword(ResetPasswordRequest request) {
        User user = userRepository.findByResetToken(request.getToken())
                .orElse(null);
        if (user == null) {
            return ApiResponse.error(PASSWORD_RESET_TOKEN_INVALID);
        }

        // 2️⃣ Kiểm tra token có hết hạn không
        if (user.getResetTokenExpiry() == null || user.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
            return ApiResponse.error(PASSWORD_RESET_TOKEN_EXPIRED);
        }

        user.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
        user.setResetToken(null);
        user.setResetTokenExpiry(null);
        userRepository.save(user);

        return ApiResponse.success(PASSWORD_RESET_SUCCESS);
    }
}

