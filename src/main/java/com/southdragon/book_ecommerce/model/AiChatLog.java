package com.southdragon.book_ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "ai_chat_logs")
public class AiChatLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;                  // Có thể null nếu khách chưa login

    @Lob
    private String userMessage;         // Nội dung người dùng hỏi

    @Lob
    private String aiResponse;          // Nội dung AI trả lời (text)

    private LocalDateTime createdAt;
}

