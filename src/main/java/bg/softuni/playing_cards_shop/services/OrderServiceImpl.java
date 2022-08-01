package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.CartNotesDto;
import bg.softuni.playing_cards_shop.models.entities.OrderEntity;
import bg.softuni.playing_cards_shop.models.entities.OrderProductEntity;
import bg.softuni.playing_cards_shop.repositories.OrderRepository;
import bg.softuni.playing_cards_shop.services.interfaces.*;
import bg.softuni.playing_cards_shop.web.exceptions.InvalidOrderException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final AddressService addressService;
    private final CartProductService cartProductService;
    private final OrderProductService orderProductService;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, AddressService addressService, CartProductService cartProductService, OrderProductService orderProductService, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.addressService = addressService;
        this.cartProductService = cartProductService;
        this.orderProductService = orderProductService;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean placeOrder(CartNotesDto cartNotesDto) {
        var user = this.userService.getCurrentUser();

        if (user.getCart().isEmpty()) {
            return false;
        }

        var address = this.addressService.findAddressById(cartNotesDto.getAddressId());
        var cart = user.getCart().stream()
                .map(cp -> this.modelMapper.map(cp, OrderProductEntity.class))
                .collect(Collectors.toSet());
        this.orderProductService.saveAll(cart);
        this.cartProductService.deleteCartProducts(user.getCart());

        var order = new OrderEntity()
                .setOrderTime(Instant.now())
                .setNotes(cartNotesDto.getNotes())
                .setAddress(address)
                .setCustomer(user)
                .setProducts(cart)
                .setPrice(user.getCart().stream()
                        .map(c -> c.getOffer().getPrice().multiply(BigDecimal.valueOf(c.getQuantity())))
                        .reduce(BigDecimal.valueOf(0), BigDecimal::add));

        this.orderRepository.save(order);
        return true;
    }
}
