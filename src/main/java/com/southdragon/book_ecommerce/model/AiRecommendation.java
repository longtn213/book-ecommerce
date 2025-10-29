package com.southdragon.book_ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "ai_recommendations")
public class AiRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_log_id")
    private AiChatLog chatLog;          // Gợi ý này thuộc cuộc chat nào

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;                  // Quyển sách AI đề xuất

    private Double confidenceScore;     // Điểm “phù hợp” nếu bạn muốn lưu

    private LocalDateTime createdAt;
}

