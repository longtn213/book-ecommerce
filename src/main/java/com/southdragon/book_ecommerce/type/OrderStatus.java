package com.southdragon.book_ecommerce.type;

public enum OrderStatus {
    PENDING,       // Mới tạo
    PAID,          // Đã thanh toán
    SHIPPING,      // Đang giao hàng
    COMPLETED,     // Hoàn tất
    CANCELLED      // Hủy đơn
}
