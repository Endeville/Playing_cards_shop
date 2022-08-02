package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.CartNotesDto;
import bg.softuni.playing_cards_shop.models.entities.OrderEntity;
import bg.softuni.playing_cards_shop.models.entities.OrderProductEntity;
import bg.softuni.playing_cards_shop.models.entities.enums.OrderStatus;
import bg.softuni.playing_cards_shop.models.views.TableOrderDto;
import bg.softuni.playing_cards_shop.repositories.OrderRepository;
import bg.softuni.playing_cards_shop.services.interfaces.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final AddressService addressService;
    private final CartProductService cartProductService;
    private final OrderProductService orderProductService;
    private final OfferService offerService;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, AddressService addressService, CartProductService cartProductService, OrderProductService orderProductService, OfferService offerService, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.addressService = addressService;
        this.cartProductService = cartProductService;
        this.orderProductService = orderProductService;
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean placeOrder(CartNotesDto cartNotesDto) {
        var user = this.userService.getCurrentUser();

        if (user.getCart().isEmpty()) {
            return false;
        }

        var address = this.addressService.findAddressById(cartNotesDto.getAddressId());
        var orderProducts = user.getCart().stream()
                .map(cp -> this.modelMapper.map(cp, OrderProductEntity.class))
                .collect(Collectors.toSet());
        this.cartProductService.deleteCartProducts(user.getCart());

        for (OrderProductEntity orderProduct : orderProducts) {
            orderProduct.setQuantity(this.offerService.decreaseQuantity(orderProduct.getOffer(), orderProduct.getQuantity()));
        }

        var order = new OrderEntity()
                .setOrderTime(Instant.now())
                .setNotes(cartNotesDto.getNotes())
                .setAddress(address)
                .setCustomer(user)
                .setProducts(orderProducts)
                .setPrice(orderProducts.stream()
                        .map(op -> op.getOffer().getPrice().multiply(BigDecimal.valueOf(op.getQuantity())))
                        .reduce(BigDecimal.valueOf(0), BigDecimal::add))
                .setStatus(OrderStatus.PLACED);

        var orderEntity=this.orderRepository.save(order);
        for (OrderProductEntity orderProduct : orderProducts) {
            orderProduct.setOrder(orderEntity);
        }
        this.orderProductService.saveAll(orderProducts);
        return true;
    }

    @Override
    public List<TableOrderDto> findCurrentUserOrders() {
        return this.userService.getCurrentUser().getOrders()
                .stream()
                .map(o -> this.modelMapper.map(o, TableOrderDto.class))
                .collect(Collectors.toList());
    }
}
