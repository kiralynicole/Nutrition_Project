package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int idUser;

    @Column(nullable=false)
    private int idProduct;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int quantity;


}
