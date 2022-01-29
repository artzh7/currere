package com.artzh7.currere.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter @NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderStatus;
    private String restaurantName;
    private String clientAddress;
    private String clientPhoneNumber;

    public Booking(String restaurantName, String clientAddress, String clientPhoneNumber) {
        this.orderStatus = "accepted";
        this.restaurantName = restaurantName;
        this.clientAddress = clientAddress;
        this.clientPhoneNumber = clientPhoneNumber;
    }
}
