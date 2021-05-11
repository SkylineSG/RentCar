package com.service.information;

import com.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailSender implements Notificators {
    public JavaMailSender javaMailSender;

    @Override
    public void notifyOrderCreated(Order order) {
        StringBuilder text = new StringBuilder();
        text.append("We have received order for:");
        order.getOrderItems().forEach((t) ->
                text.append("\n" + t));
        text.append("\n If you had not ordered those items please inform us immediately.");

        String message = text.toString();

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(order.getUser().getEmail());
            mailMessage.setSubject("new order " + order.getId());
            mailMessage.setText(message);

            javaMailSender.send(mailMessage);
        } catch (MailException e) {
            System.out.println(e);
        }

        /*System.out.println("We have received order for:");
        for (OrderItem item : order.getOrderItems()) {
            System.out.println(item);
        }
        System.out.println("If you had not ordered those items please inform us immediately.");*/
    }

    @Override
    public void notifyOrderVerified(Order order) {
        StringBuilder text = new StringBuilder();
        text.append(order.getUser().getUserName() + "  your order " + order.getId() + " has been verified. " +
                "\nPlease proceed with chosen payment method. " +
                "(If payement was already done please wait, we may need to process it at our side)" +
                "The details of your order are:");
        text.append(order.toString());

        String message = text.toString();

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(order.getUser().getEmail());
            mailMessage.setSubject("order " + order.getId() + "confirmed");
            mailMessage.setText(message);

            javaMailSender.send(mailMessage);
        } catch (MailException e) {
            System.out.println(e);
        }

        /*System.out.println(user.getUserName() + "  your order " + order.getId() + " has been verified. " +
                    "\nPlease proceed with chosen payment method. " +
                    "(If payement was already done please wait, we may need to process it at our side)");
        System.out.println("The details of your order are:");
        System.out.println(order.toString());*/
    }

    @Override
    public void notifyOrderPaid(Order order) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(order.getUser().getEmail());
            mailMessage.setSubject("payment received");
            mailMessage.setText(order.getUser().getUserName() + " payment for your order " + order.getId() + " has been confirmed.");

            javaMailSender.send(mailMessage);
        } catch (MailException e) {
            System.out.println(e);
        }

        /*System.out.println("SENDING EMAIL");
        System.out.println(user.getUserName() + " payment for your order " + order.getId() + " has been confirmed.");*/
    }

    @Override
    public void notifyOrderSent(Order order) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(order.getUser().getEmail());
            mailMessage.setSubject("order " + order.getId() + "has been sent");
           // mailMessage.setText(order.getUser().getUserName() + " your order " + order.getId() + " has been sent to \n" + order.getUser().getAddress() + ".");

            javaMailSender.send(mailMessage);
        } catch (MailException e) {
            System.out.println(e);
        }

        //System.out.println(user.getUserName() + " your order " + order.getId() + " has been sent to \n" + user.getAddress() + ".");
    }
}

