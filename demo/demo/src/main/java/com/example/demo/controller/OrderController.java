package com.example.demo.controller;


import com.example.demo.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The {@code OrderController} class is a REST controller that handles HTTP requests related to orders.
 * It uses {@link OrderService} to process business logic associated with orders.
 * <p>
 * This controller is mapped to handle requests starting with the "/orders" path.
 * </p>
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    /**
     * The service that contains order-related business logic.
     */
    private OrderService orderService;


}
