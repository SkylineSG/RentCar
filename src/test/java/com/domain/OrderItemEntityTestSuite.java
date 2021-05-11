package com.domain;

import com.RentalApplication;
import com.repository.CarRepository;
import com.repository.OrderItemRepository;
import com.repository.OrderRepository;
import com.service.CarDbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = RentalApplication.class)
public class OrderItemEntityTestSuite {
    @Autowired
    private  OrderRepository orderRepository;
    @Autowired
    private  CarRepository carRepository;
    @Autowired
    private  CarDbService carDbService;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Test
    public void testAddItemToTheOrder() {
        Car car1 = new Car(1234L, "Bmw", "Normal Car", 2000.0);
        Car car2 = new Car(234456L, "Mercedes", "Premium Car", 5000.0);
        carRepository.save(car1);
        carRepository.save(car2);

        //When
        OrderItem orderItems1 = new OrderItem();
        orderItemRepository.save(orderItems1);
        OrderItem orderItems2 = new OrderItem();
        orderItemRepository.save(orderItems2);

        Car resultCar1 = orderItemRepository.findAll().get(0).getCar();
        Car resultCar2 = orderItemRepository.findAll().get(1).getCar();

        //Then
        Assertions.assertEquals(car1.getCarName(), "Bmw");
        Assertions.assertEquals("Mercedes", car2.getCarName());

        orderItemRepository.deleteAll();

    }

    @Test
    public void testSaveOrderItem() {
        //Given
        OrderItem orderItem = new OrderItem();

        //When
        orderItemRepository.save(orderItem);

        //Then
        Long id = orderItem.getId();
        Optional<OrderItem> findOrderItem = orderItemRepository.findById(id);
        Assertions.assertTrue(findOrderItem.isPresent());

        //CleanUp
        orderItemRepository.deleteAll();
    }

    @Test
    public void testFindAllOrderItem() {
        //Given
        OrderItem orderItem1 = new OrderItem();
        OrderItem orderItem2 = new OrderItem();
        OrderItem orderItem3 = new OrderItem();
        OrderItem orderItem4 = new OrderItem();
        OrderItem orderItem5 = new OrderItem();

        //When
        orderItemRepository.save(orderItem1);
        orderItemRepository.save(orderItem2);
        orderItemRepository.save(orderItem3);
        orderItemRepository.save(orderItem4);
        orderItemRepository.save(orderItem5);
        List<OrderItem> orderItemsList = orderItemRepository.findAll();

        //Then
        Assertions.assertEquals(5, orderItemsList.size());

        //CleanUp
        orderItemRepository.deleteAll();
    }

    @Test
    public void testFindOrderItemById() {
        //Given
        OrderItem orderItem = new OrderItem();

        //When
        orderItemRepository.save(orderItem);
        Long orderItemId = orderItem.getId();

        //Then
        Assertions.assertEquals(orderItem.getId(), orderItemRepository.findById(orderItemId).get().getId());

        //CleanUp
        orderItemRepository.deleteAll();
    }

}
