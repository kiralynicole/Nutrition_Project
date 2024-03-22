package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="product")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true, nullable=false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private int price;

    @Column(nullable=false)
    private int stock;

    @Column(nullable = false)
    private LocalDate expirationDate;



   // @Column(nullable = false)
   // private String image;

}
