package com.artzh7.currere.entity;

import lombok.Getter;

public enum OrderStatus {
    CANCELLED("Отменен"),
    ACCEPTED("Принят"),
    IN_WORK("Назначен"),
    COURIER_ARRIVED("Ожидает выдачи"),
    IN_TRANSIT("В пути"),
    FINISHED("Закрыт");

    @Getter
    private final String title;

    OrderStatus(String title) {
        this.title = title;
    }
}
