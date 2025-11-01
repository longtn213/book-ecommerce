package com.southdragon.book_ecommerce.dto.user;

import com.southdragon.book_ecommerce.model.Order;
import com.southdragon.book_ecommerce.model.OrderItem;
import com.southdragon.book_ecommerce.type.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderUserSummaryDto {
    private String orderCode;            // Ví dụ: ORD-2025-0001

    private String fullName;

    private OrderStatus status;          // PENDING, PAID, SHIPPING...

    private Double totalAmount;          // Tổng tiền đơn hàng

    private boolean paid;                // true nếu đã thanh toán (tùy logic bạn xử lý)

    private String shippingAddress;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<OrderItemUserSummaryDto> orderItemUserSummaryDtoList = new ArrayList<>();

    public static OrderUserSummaryDto fromEntity(Order order) {
        return OrderUserSummaryDto.builder()
                .orderCode(order.getOrderCode())
                .fullName(order.getUser().getFullName())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .paid(order.isPaid())
                .shippingAddress(order.getShippingAddress())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .orderItemUserSummaryDtoList(order.getItems().stream().map(OrderItemUserSummaryDto::fromEntity).toList())
                .build();
    }
}
