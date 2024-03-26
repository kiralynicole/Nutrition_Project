package com.example.demo.interfaces;

import com.example.demo.model.Order;

import java.util.List;

public interface OrderInterface {
    Order getOrderById(int id);

    List<Order> getAllOrders();

    int getUserOfOrder(int id);

    String deleteOrder(int id);

    Order createOrder(Order o);
}
