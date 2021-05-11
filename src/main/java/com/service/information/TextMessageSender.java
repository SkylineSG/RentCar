package com.service.information;

import com.domain.Order;
import org.springframework.stereotype.Service;

@Service
public class TextMessageSender implements Notificators {
    @Override
    public void notifyOrderCreated(Order order) {
        System.out.println("new order " + order.getId() + " was created. Please find more details on provided e-mail: " + order.getUser().getEmail());
    }

    @Override
    public void notifyOrderVerified(Order order) {
        System.out.println("TEXT MESSAGE SEND TO CUSTOMER");
        System.out.println("order " + order.getId() + "has been verified.");
    }

    @Override
    public void notifyOrderPaid(Order order) {
        System.out.println("TEXT MESSAGE SEND TO CUSTOMER");
        System.out.println("payment for order " + order.getId() + "has been confirmed.");
    }

    @Override
    public void notifyOrderSent(Order order) {
        System.out.println("TEXT MESSAGE SEND TO CUSTOMER");
   //     System.out.println("order " + order.getId() + "has been sent.");
    }
}
