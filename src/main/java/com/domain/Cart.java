package com.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cart")
@Entity
public class Cart {
    @Id
    @GeneratedValue
    @Column(nullable = false,name = "Cart_id")
    private Long id;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "User_id")
    private User user;

    @ManyToMany(
            targetEntity = Car.class,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "products_In_Cart",
            joinColumns = @JoinColumn(name = "Cart_id")
    )
    private List<Car> cars;
}