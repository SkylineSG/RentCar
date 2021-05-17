package com.domain;

import com.RentalApplication;
import com.repository.OrderRepository;
import com.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RentalApplication.class)
 class OrderEntityTestSuite {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    private void testOrderConnections() {
        //Given
        User user1 = new User(1L, "User1", true, 1234L);
        User user2 = new User(2L, "User2", false, 2345L);
        user1.setAddress("Somewhere in the world");
        user1.setEmail("SomeMail@mail");
        user1.setPhoneNumber("118913");
        user2.setAddress("Somewhere in the world");
        user2.setEmail("SomeMail@mail");
        user2.setPhoneNumber("118913");
        userRepository.save(user1);
        userRepository.save(user2);

        //When
        Order order1 = new Order(111L, user2, new ArrayList<>(), true, true, true);
        Order order2 = new Order(222L, user1, new ArrayList<>(), true, true, true);
        order1.setShipmentAddress("Somewhere in the world");
        order2.setShipmentAddress("Somewhere in the world");
        orderRepository.save(order1);
        orderRepository.save(order2);

        //Then
        User resultUser1 = orderRepository.findAll().get(0).getUser();
        User resultUser2 = orderRepository.findAll().get(1).getUser();

        assertEquals("User1", resultUser2.getUserName());
        assertEquals(resultUser1.getUserKey(), user2.getUserKey());

        //CleanUp
        orderRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    private void testSaveOrder() {
        //Given
        Order order = new Order();
        order.setShipmentAddress("Somewhere in the world");

        //When
        orderRepository.save(order);

        //Then
        Long id = order.getId();
        Optional<Order> readOrder = orderRepository.findById(id);
        assertTrue(readOrder.isPresent());

        //CleanUp
        orderRepository.deleteAll();
    }

    @Test
    private void testFindOrderById() {
        //Given
        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        Order order4 = new Order();
        order1.setShipmentAddress("Somewhere in the world");
        order2.setShipmentAddress("Somewhere in the world");
        order3.setShipmentAddress("Somewhere in the world");
        order4.setShipmentAddress("Somewhere in the world");

        //When
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderRepository.save(order4);
        Long id = order3.getId();
        Optional<Order> readOrder = orderRepository.findById(id);

        //Then
        assertEquals(order3.getId(), orderRepository.findById(id).get().getId());

        //CleanUp
        orderRepository.deleteAll();
        }

    @Test
    private void testOrderFindAll() {
        //Given
        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        Order order4 = new Order();
        order1.setShipmentAddress("Somewhere in the world");
        order2.setShipmentAddress("Somewhere in the world");
        order3.setShipmentAddress("Somewhere in the world");
        order4.setShipmentAddress("Somewhere in the world");

        //When
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderRepository.save(order4);
        List<Order> orders = orderRepository.findAll();

        //Then
        assertEquals(4, orders.size());
        assertNotEquals(2, orders.size());

        //CleanUp
        orderRepository.deleteAll();
    }
}
