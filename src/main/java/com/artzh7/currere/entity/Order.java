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

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courier_id")
    private User courier;

    private String restaurantName;
    private String restaurantAddress;
    private String restaurantPhoneNumber;
    @Column(columnDefinition = "TEXT")
    private String restaurantComment;

    private String clientAddress;
    private String clientPhoneNumber;
    private String orderComment;

    public Order(User author,
                 String restaurantName, String restaurantAddress,
                 String restaurantPhoneNumber, String restaurantComment,
                 String clientAddress, String clientPhoneNumber, String orderComment) {
        this.orderStatus = OrderStatus.ACCEPTED;
        this.author = author;
        this.courier = null;

        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantPhoneNumber = restaurantPhoneNumber;
        this.restaurantComment = restaurantComment;

        this.clientAddress = clientAddress;
        this.clientPhoneNumber = clientPhoneNumber;
        this.orderComment = orderComment;
    }
}
