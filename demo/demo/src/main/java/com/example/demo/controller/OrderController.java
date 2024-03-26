package com.example.demo.controller;


import com.example.demo.interfaces.OrderInterface;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The {@code OrderController} class is a REST controller that handles HTTP requests related to orders.
 * It uses {@link OrderService} to process business logic associated with orders.
 * <p>
 * This controller is mapped to handle requests starting with the "/orders" path.
 * </p>
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    /**
     * The service that contains order-related business logic.
     */
    private OrderInterface orderInterface;


    @GetMapping("/{id}")
    public Order findOrderById(@PathVariable int id){ return orderInterface.getOrderById(id);}

    @GetMapping
    public List<Order> findAllOrders(){return orderInterface.getAllOrders(); }


    @GetMapping("/findClient/{id}")
    public int findUserOfOrder(@PathVariable int id){return orderInterface.getUserOfOrder(id);}

    @DeleteMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable int id){ return orderInterface.deleteOrder(id);}

    @PostMapping("/addOrder")
    public Order addOrder(@RequestBody Order o){return orderInterface.createOrder(o);}


}
