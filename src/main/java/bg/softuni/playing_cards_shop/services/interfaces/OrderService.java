package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.CartNotesDto;
import bg.softuni.playing_cards_shop.models.views.OrderDetailsDto;
import bg.softuni.playing_cards_shop.models.views.TableOrderDto;

import java.util.List;

public interface OrderService {

    String OBJECT_NAME_ORDER="order";

    boolean placeOrder(CartNotesDto cartNotesDto);

    List<TableOrderDto> findCurrentUserOrders();

    List<TableOrderDto> findOrdersForCurrentUser();

    OrderDetailsDto findOrderDetailsById(Long id);
}
