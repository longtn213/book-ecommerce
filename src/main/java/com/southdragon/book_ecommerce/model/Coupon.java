package com.southdragon.book_ecommerce.model;

import com.southdragon.book_ecommerce.type.CouponStatus;
import com.southdragon.book_ecommerce.type.DiscountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String description;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    private Double discountValue;
    private Double minOrderTotal;
    private Double maxDiscountAmount;
    private Integer usageLimit;
    private Integer usagePerUserLimit;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @Enumerated(EnumType.STRING)
    private CouponStatus status;
}


