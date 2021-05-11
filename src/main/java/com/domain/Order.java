package com.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(nullable = false,name = "ID", unique = true)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(
            targetEntity = OrderItem.class,
            mappedBy = "order"
    )
    private List<OrderItem> orderItems;

    @Column(name = "is_payment_confirmed")
    private boolean paid = false;

    @Column(name = "is_verified")
    private boolean verified = false;

    @Column(name = "is_carSent")
    private boolean sent = false;

    @Column(name = "Shipment_to")
    private String shipmentAddress;

    public Order(User user, List<OrderItem> orderItems) {
        this.user = user;
        this.orderItems = orderItems;
    }

    public Order(@Nullable Long id, User user, List<OrderItem> orderItems, boolean paid, boolean verified, boolean sent) {
        this.id = id;
        this.user = user;
        this.orderItems = orderItems;
        this.paid = paid;
        this.verified = verified;
        this.sent = sent;
    }
}
