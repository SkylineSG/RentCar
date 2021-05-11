package com.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User{
    @Id
    @GeneratedValue
    @Column(nullable = false,name = "User_id")
    private Long id;

    @Column(nullable = false,name = "Username")
    private String userName;

    @Column(name = "Status")
    @Nullable
    private boolean status=true;

    @Nullable
    @Column(name = "userKey")
    private Long userKey;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<Cart> carts;

    public User(@Nullable String userName) {
        this.userName = userName;
    }

    public User(@Nullable Long id, @Nullable String userName, @Nullable boolean status, @Nullable Long userKey) {
        this.id = id;
        this.userName = userName;
        this.status = status;
        this.userKey = userKey;
    }
}
