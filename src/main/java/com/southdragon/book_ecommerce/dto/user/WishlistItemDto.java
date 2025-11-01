package com.southdragon.book_ecommerce.dto.user;

import com.southdragon.book_ecommerce.model.Book;
import com.southdragon.book_ecommerce.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishlistItemDto {
    private String bookName;

    private LocalDateTime createdAt;

}
