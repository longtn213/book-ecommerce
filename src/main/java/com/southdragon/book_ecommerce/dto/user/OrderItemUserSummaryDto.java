package com.southdragon.book_ecommerce.dto.user;

import com.southdragon.book_ecommerce.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemUserSummaryDto {
    private String bookTitleSnapshot;   // Tên sách tại thời điểm mua
    private Double price;               // Giá tại thời điểm mua
    private Integer quantity;
    private Double total;

    public static OrderItemUserSummaryDto fromEntity(OrderItem orderItem) {
        return OrderItemUserSummaryDto.builder()
                .bookTitleSnapshot(orderItem.getBookTitleSnapshot())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .total(orderItem.getTotal())
                .build();
    }
}
