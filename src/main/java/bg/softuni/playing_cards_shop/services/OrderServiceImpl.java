package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.CartNotesDto;
import bg.softuni.playing_cards_shop.models.entities.OrderEntity;
import bg.softuni.playing_cards_shop.models.entities.OrderProductEntity;
import bg.softuni.playing_cards_shop.models.entities.UserEntity;
import bg.softuni.playing_cards_shop.models.entities.enums.OrderStatus;
import bg.softuni.playing_cards_shop.models.views.OrderDetailsDto;
import bg.softuni.playing_cards_shop.models.views.TableOrderDto;
import bg.softuni.playing_cards_shop.repositories.OrderRepository;
import bg.softuni.playing_cards_shop.services.interfaces.*;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
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
                .map(cp -> {
                    var map = this.modelMapper.map(cp, OrderProductEntity.class);
                    map.setId(null);
                    return map;
                })
                .collect(Collectors.groupingBy(p->p.getOffer().getSeller()));

        this.cartProductService.deleteCartProducts(user.getCart());

        for (List<OrderProductEntity> value : orderProducts.values()) {
            for (OrderProductEntity orderProductEntity : value) {
                orderProductEntity.setQuantity(this.offerService.decreaseQuantity(orderProductEntity.getOffer(), orderProductEntity.getQuantity()));
            }
        }

        for (UserEntity seller : orderProducts.keySet()) {
            var order = new OrderEntity()
                    .setOrderTime(Instant.now())
                    .setNotes(cartNotesDto.getNotes())
                    .setAddress(address)
                    .setCustomer(user)
                    .setProducts(new HashSet<>(orderProducts.get(seller)))
                    .setPrice(orderProducts.get(seller).stream()
                            .map(op -> op.getOffer().getPrice().multiply(BigDecimal.valueOf(op.getQuantity())))
                            .reduce(BigDecimal.valueOf(0), BigDecimal::add))
                    .setStatus(OrderStatus.PLACED)
                    .setSeller(seller);


            var orderEntity=this.orderRepository.save(order);

            for (OrderProductEntity orderProduct : orderProducts.get(seller)) {
                orderProduct.setOrder(orderEntity);
            }

            this.orderProductService.saveAll(new HashSet<>(orderProducts.get(seller)));
        }

        return true;
    }

    @Override
    public List<TableOrderDto> findCurrentUserOrders() {
        return this.userService.getCurrentUser().getPlacedOrders()
                .stream()
                .sorted((o1,o2)->o2.getOrderTime().compareTo(o1.getOrderTime()))
                .map(o -> this.modelMapper.map(o, TableOrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TableOrderDto> findOrdersForCurrentUser() {
        var user=this.userService.getCurrentUser();
        return this.orderRepository.findOrderEntitiesBySellerOrderByOrderTimeDesc(user)
                .stream()
                .map(o->this.modelMapper.map(o, TableOrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailsDto findOrderDetailsById(Long id) {
        return this.modelMapper.map(this.orderRepository.findById(id)
                .orElseThrow(()->new ObjectNotFoundException(OBJECT_NAME_ORDER)), OrderDetailsDto.class);
    }
}
