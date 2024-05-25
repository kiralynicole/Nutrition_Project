package com.example.demo.service;

import com.example.demo.interfaces.OrderInterface;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * The {@code OrderService} class implements the {@link OrderInterface} to provide
 * concrete functionalities for managing orders. It utilizes the {@link OrderRepository}
 * for persistence operations.
 */
@Service
@RequiredArgsConstructor
public class OrderService implements OrderInterface {

    private final OrderRepository orderRepository;

    /**
     * Retrieves an order by its ID. If the order is not found, a {@code RuntimeException} is thrown.
     *
     * @param id the ID of the order to retrieve.
     * @return the {@link Order} object with the specified ID.
     * @throws RuntimeException if the order is not found.
     */
    @Override
    public Order getOrderById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found for id: " + id));
    }

    /**
     * Retrieves all orders present in the system.
     *
     * @return a list of all {@link Order} objects.
     */
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Retrieves the user ID associated with the given order ID.
     *
     * @param id the ID of the order whose user ID is to be retrieved.
     * @return the user ID associated with the specified order.
     */
    @Override
    public int getUserOfOrder(int id) {
        Order o = getOrderById(id);
        return o.getIdUser();
    }

    /**
     * Deletes an order based on its ID and returns a confirmation message.
     *
     * @param id the ID of the order to delete.
     * @return a confirmation message indicating the deletion.
     */
    @Override
    public String deleteOrder(int id) {
        orderRepository.deleteById(id);
        return "Order with id " + id + " removed";
    }

    /**
     * Creates and persists a new order in the system.
     *
     * @param o the {@link Order} object to create and save.
     * @return the saved {@link Order} object.
     */
    @Override
    public Order createOrder(Order o) {
        o.setDate(LocalDate.now());
        return orderRepository.save(o);
    }

    @Override
    public Order updateOrder(Order o) {
        Order order = orderRepository.findById(o.getId())
                .orElse(null);

        order.setIdProduct(o.getIdProduct());
        order.setDate(o.getDate());
        order.setQuantity(o.getQuantity());
        order.setIdUser(o.getIdUser());

        return orderRepository.save(order);
    }

}
