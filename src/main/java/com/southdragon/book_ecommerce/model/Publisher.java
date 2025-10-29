package com.southdragon.book_ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;           // Tên NXB
    private String address;        // Địa chỉ
    private String contactEmail;   // Email liên hệ
    private String contactPhone;   // SĐT liên hệ
}
