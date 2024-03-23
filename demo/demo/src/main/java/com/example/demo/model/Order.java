package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * The {@code Order} class represents an order entity mapped to the "orders" table in the database.
 * <p>
 * This entity stores information about orders, including the ID of the user making the order,
 * the ID of the product being ordered, the date of the order, and the quantity ordered. It uses
 * Lombok annotations to automatically generate getters, setters, a no-arguments constructor, an
 * all-arguments constructor, a builder pattern for object creation, and a {@code toString} method.
 * </p>
 */

@Entity
@Table(name="orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Order {

    /**
     * The primary key of the order, automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The ID of the user who made the order.
     * This field is not nullable.
     */
    @Column(nullable = false)
    private int idUser;

    /**
     * The ID of the product being ordered.
     * This field is not nullable.
     */
    @Column(nullable=false)
    private int idProduct;

    /**
     * The date when the order was placed.
     * This field is not nullable.
     */
    @Column(nullable = false)
    private LocalDate date;

    /**
     * The quantity of the product ordered.
     * This field is not nullable.
     */
    @Column(nullable = false)
    private int quantity;


}
