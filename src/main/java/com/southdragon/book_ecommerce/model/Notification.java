package com.southdragon.book_ecommerce.model;

import com.southdragon.book_ecommerce.type.NotificationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private boolean isRead;

    @CreatedDate
    private LocalDateTime createdAt;
}


