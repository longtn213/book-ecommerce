package com.southdragon.book_ecommerce.constant;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class MessageConstant {

    // ==== AUTH MESSAGES ====
    public static final String AUTH_REGISTER_SUCCESS = "Đăng ký tài khoản thành công!";
    public static final String AUTH_REGISTER_DUPLICATE = "Tên đăng nhập hoặc email đã tồn tại!";
    public static final String AUTH_LOGIN_SUCCESS = "Đăng nhập thành công!";
    public static final String AUTH_LOGIN_FAILED = "Mật khẩu hoặc tài khoản không đúng! Vui lòng kiểm tra lại.";
    public static final String AUTH_USER_NOT_FOUND = "Không tìm thấy tài khoản!";
    public static final String AUTH_EMAIL_NOT_FOUND = "Email không tồn tại!";

    public static final String USER_NOT_FOUND = "Người dùng không tồn tại!";
    public static final String USER_PROFILE_SUCCESS = "Lấy thành công thông tin người dùng!";

    // ==== PASSWORD RESET ====
    public static final String PASSWORD_RESET_EMAIL_SENT = "Email khôi phục mật khẩu đã được gửi đến: ";
    public static final String PASSWORD_RESET_SUCCESS = "Đặt lại mật khẩu thành công!";
    public static final String PASSWORD_RESET_TOKEN_INVALID = "Token không hợp lệ!";
    public static final String PASSWORD_RESET_TOKEN_EXPIRED = "Token đã hết hạn!";

    // ==== COMMON / GENERIC ====
    public static final String COMMON_INTERNAL_ERROR = "Đã xảy ra lỗi, vui lòng thử lại sau.";
    public static final String COMMON_ACCESS_DENIED = "Bạn không có quyền truy cập.";
    public static final String COMMON_DATA_NOT_FOUND = "Không tìm thấy dữ liệu yêu cầu.";
}
