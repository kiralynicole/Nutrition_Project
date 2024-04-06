/**
 * The {@code OrderInterface} interface outlines the essential operations for managing orders,
 * including CRUD (Create, Read, Update, Delete) operations on {@link Order} objects. It facilitates
 * the retrieval of order information, listing all orders, getting the user associated with an order,
 * deletion of an order, and creation of a new order.
 */
package com.example.demo.interfaces;

import com.example.demo.model.Order;
import java.util.List;

public interface OrderInterface {

    /**
     * Retrieves an {@link Order} object by its unique ID.
     *
     * @param id The ID of the order to retrieve.
     * @return The {@link Order} object with the specified ID, or {@code null} if no such order exists.
     */
    Order getOrderById(int id);

    /**
     * Retrieves a list of all {@link Order} objects in the system.
     *
     * @return A {@link List} of {@link Order} objects. This list may be empty if no orders are present.
     */
    List<Order> getAllOrders();

    /**
     * Retrieves the ID of the user associated with a given order.
     *
     * @param id The ID of the order whose user ID is to be retrieved.
     * @return The ID of the user associated with the specified order.
     */
    int getUserOfOrder(int id);

    /**
     * Deletes an order from the system based on its ID.
     *
     * @param id The ID of the order to be deleted.
     * @return A {@link String} indicating the result of the deletion operation.
     */
    String deleteOrder(int id);

    /**
     * Creates a new order in the system.
     *
     * @param o The {@link Order} object to be added to the system.
     * @return The newly created {@link Order} object.
     */
    Order createOrder(Order o);
}
