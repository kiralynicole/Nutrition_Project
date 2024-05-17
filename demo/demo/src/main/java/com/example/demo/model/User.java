package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
/**
 * The {@code User} class represents a user entity in the application.
 * <p>
 * It is mapped to the "users" table in the database. This entity stores information about users,
 * including their name, address, email, password, and phone number. The {@link Data}, {@link NoArgsConstructor},
 * {@link AllArgsConstructor}, {@link Builder}, and {@link ToString} annotations from Lombok are used
 * to automatically generate boilerplate code such as getters, setters, constructors, a builder pattern
 * for object creation, and a toString method.
 * </p>
 * <p>
 * Unique constraints are applied to the "name" and "email" fields to ensure no two users have the
 * same name or email.
 * </p>
 */
@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
@ToString
public class User {

    /**
     * The unique identifier for the user. This field is automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The user's name. This field must be unique and is not nullable.
     */
    @Column(unique = true, nullable=false)
    private String name;

    /**
     * The user's address. This field is not nullable.
     */
    @Column ()
    private String address;

    /**
     * The user's email address. This field must be unique and is not nullable.
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * The user's password. This field is not nullable.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The user's phone number. This field is not nullable.
     */
    @Column()
    private String phone;

    /**
     * Indicates whether the user has administrative privileges. This field cannot be null.
     */
    @Column(nullable = false)
    private boolean isAdmin;

    /**
     * Indicates whether the user is associated with sales. This field cannot be null and helps in
     * segregating user roles within the system.
     */
    @Column(nullable = false)
    private boolean isSale;

    /**
     * Constructor to initialize a User with full attributes without using Lombok's AllArgsConstructor.
     * This constructor manually assigns each field.
     *
     * @param name     the user's name
     * @param address  the user's address
     * @param email    the user's email address
     * @param pass the user's password
     * @param phone    the user's phone number
     * @param isAdmin  indicates if the user has administrative privileges
     * @param isSale   indicates if the user is involved in sales
     */
    public User(String name, String address, String email, String pass, String phone, boolean isAdmin, boolean isSale){
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.isSale = isSale;
        this.isAdmin = isAdmin;
        this.password = pass;

    }

    public User(String name, String email, String pass){
        this.name = name;
        this.email = email;
        this.password = pass;
    }



}
