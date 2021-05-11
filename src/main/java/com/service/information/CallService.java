package com.service.information;

import com.domain.Order;
import org.springframework.stereotype.Service;

@Service
public class CallService implements Notificators {
    @Override
    public void notifyOrderCreated(Order order) {
        System.out.println("Calling " + order.getUser().getPhoneNumber());
    }

    @Override
    public void notifyOrderVerified(Order order) {
        System.out.println("Calling " + order.getUser().getPhoneNumber());
    }

    @Override
    public void notifyOrderPaid(Order order) {
        System.out.println("Calling " + order.getUser().getPhoneNumber());
    }

    @Override
    public void notifyOrderSent(Order order) {
        System.out.println("Calling " + order.getUser().getPhoneNumber());
    }
}
