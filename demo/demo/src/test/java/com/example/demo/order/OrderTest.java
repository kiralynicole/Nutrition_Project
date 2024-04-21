package com.example.demo.order;

import com.example.demo.interfaces.OrderInterface;
import com.example.demo.model.Category;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for the {@link OrderService} class.
 */
public class OrderTest {
    @Mock
    private OrderRepository orderRepository;

    private OrderInterface orderInterface;

    /**
     * Initializes mocks and sets up the {@link OrderService} instance before each test.
     */
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        orderInterface = new OrderService(orderRepository);
    }

    /**
     * Test the save functionality of an order by verifying that the repository's save method is called with the correct order.
     */
    @Test
    public void saveOrderTest(){
        Order expectedOrder = new Order(1, 2, LocalDate.of(2024,10, 5), 10);

        //  when(OrderRepository.save(expectedOrder)).thenReturn(expectedOrder);
        orderInterface.createOrder(expectedOrder);

        verify(orderRepository).save(expectedOrder);
        //assertEquals(expectedOrder, savedOrder);

    }
    /**
     * Test the update functionality by ensuring the findById and save methods are called correctly.
     */
    @Test
    public void updateOrderTest(){
        Order existingOrder = new Order(1,1, 2, LocalDate.of(2024,10, 5), 10);
        Order updatedOrder = new Order(1,1, 3, LocalDate.of(2024,10, 5), 5);


        when(orderRepository.findById(existingOrder.getId())).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(updatedOrder)).thenReturn(updatedOrder);

        orderInterface.updateOrder(updatedOrder);

        verify(orderRepository).findById(existingOrder.getId());
        verify(orderRepository).save(updatedOrder);

        //assertEquals(existingOrder.getId(), res.getId());
        //assertEquals(expectedOrder, savedOrder);
    }
    /**
     * Test the delete functionality by verifying that the repository's deleteById method is invoked.
     */
    @Test
    public void deletedOrderTest(){
        Order existingOrder = new Order(1, 2, LocalDate.of(2024,10, 5), 10);

        when(orderRepository.findById(existingOrder.getId())).thenReturn(Optional.of(existingOrder));

        orderInterface.deleteOrder(existingOrder.getId());

        verify(orderRepository).deleteById(existingOrder.getId());
    }
    /**
     * Test the getOrderById functionality by verifying that the repository's findById method is called.
     */
    @Test
    public void getIdOrderTest(){
        Order existingOrder = new Order(1, 2, LocalDate.of(2024,10, 5), 10);

        when(orderRepository.findById(existingOrder.getId())).thenReturn(Optional.of(existingOrder));

        orderInterface.getOrderById(existingOrder.getId());

        verify(orderRepository).findById(existingOrder.getId());
    }

    /**
     * Test getting all orders by ensuring that the findAll method of the repository is called.
     */
    @Test
    public void getAllOrderTest(){
        Order order1 = new Order(1, 2, LocalDate.of(2024,10, 5), 10);
        Order order2 = new Order(1, 2, LocalDate.of(2024,10, 5), 10);

        List<Order> orders = Arrays.asList(order1,order2);

        when(orderRepository.findAll()).thenReturn(orders);

        orderInterface.getAllOrders();

        verify(orderRepository).findAll();

    }
    /**
     * Test finding the user ID of an order by verifying the repository's findById method is called and correctly retrieves the user ID.
     */
    @Test
    public void findUserOrderTest(){
        Order order1 = new Order(1, 2, LocalDate.of(2024,10, 5), 10);

        when(orderRepository.findById(order1.getId())).thenReturn(Optional.of(order1));

        orderInterface.getUserOfOrder(order1.getId());

        verify(orderRepository).findById(order1.getId());
    }
}
