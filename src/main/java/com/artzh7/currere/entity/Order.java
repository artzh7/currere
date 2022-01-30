package com.artzh7.currere.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity @Table(name = "ordr")
@Getter @Setter @NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderStatus;
    private String restaurantName;
    private String clientAddress;
    private String clientPhoneNumber;

    public Order(String restaurantName, String clientAddress, String clientPhoneNumber) {
        this.orderStatus = "accepted";
        this.restaurantName = restaurantName;
        this.clientAddress = clientAddress;
        this.clientPhoneNumber = clientPhoneNumber;
    }
}
