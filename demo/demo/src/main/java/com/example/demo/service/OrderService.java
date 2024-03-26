package com.example.demo.service;


import com.example.demo.interfaces.OrderInterface;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderInterface {

    private final OrderRepository orderRepository;

    @Override
    public Order getOrderById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found for id: " + id));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public int getUserOfOrder(int id) {
        Order o = getOrderById(id);
        return o.getIdUser();
    }

    @Override
    public String deleteOrder(int id) {
         orderRepository.deleteById(id);
         return "Order with id " + id + " removed";
    }

    @Override
    public Order createOrder(Order o) {
        return orderRepository.save(o);
    }




}
