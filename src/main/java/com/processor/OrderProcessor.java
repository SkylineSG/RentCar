package com.processor;

import com.domain.User;
import com.domain.Order;
import com.repository.UserRepository;

import java.util.List;

public class OrderProcessor implements Observable {

    private List<User> users;
    private UserRepository userRepository;

    private Order order;


    public OrderProcessor(Order order) {
        this.order = order;

    }

    @Override
    public void notifyUsers() {
        if (order.isVerified()) {
            order.setPaid(true);
            System.out.println("Order status changed into: " + order.isVerified() + order.isPaid());
        } else {
            order.setPaid(false);
            System.out.println("Order status: " + order.isVerified() + order.isPaid());
        }

    }

    @Override
    public void removeUsers(User user) {
        users.remove(user);
    }
}