package bg.softuni.playing_cards_shop.models.dtos.rest;

import bg.softuni.playing_cards_shop.models.entities.enums.OrderStatus;

import javax.persistence.EnumType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class OrderStatusDto {
    @NotNull
    private Long id;

    @NotNull
    private OrderStatus status;


    public OrderStatus getStatus() {
        return status;
    }

    public OrderStatusDto setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OrderStatusDto setId(Long id) {
        this.id = id;
        return this;
    }
}
