package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

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


}
