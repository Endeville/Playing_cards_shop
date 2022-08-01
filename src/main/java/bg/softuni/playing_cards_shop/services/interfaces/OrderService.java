package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.CartNotesDto;

public interface OrderService {
    boolean placeOrder(CartNotesDto cartNotesDto);
}
