package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.CartNotesDto;
import bg.softuni.playing_cards_shop.models.entities.CartProductEntity;
import bg.softuni.playing_cards_shop.models.entities.OrderEntity;
import bg.softuni.playing_cards_shop.models.entities.OrderProductEntity;
import bg.softuni.playing_cards_shop.models.entities.UserEntity;
import bg.softuni.playing_cards_shop.models.entities.enums.OrderStatus;
import bg.softuni.playing_cards_shop.models.views.OrderDetailsDto;
import bg.softuni.playing_cards_shop.models.views.TableOrderDto;
import bg.softuni.playing_cards_shop.models.views.rest.OrderInfoDto;
import bg.softuni.playing_cards_shop.repositories.OrderRepository;
import bg.softuni.playing_cards_shop.services.interfaces.*;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        var result= Optional.of("");

        if (user.getCart().isEmpty()) {
            return false;
        }

        var address = this.addressService.findAddressById(cartNotesDto.getAddressId());

        var orderProducts=new HashMap<UserEntity, List<OrderProductEntity>>();

        for (CartProductEntity cartProduct : user.getCart()) {
            if(cartProduct.getQuantity()<cartProduct.getOffer().getQuantity()){
                var orderProduct=this.modelMapper.map(cartProduct, OrderProductEntity.class);

                orderProduct.setId(null);
                orderProduct.setQuantity(this.offerService.decreaseQuantity(orderProduct.getOffer(), orderProduct.getQuantity()));

                orderProducts.putIfAbsent(orderProduct.getOffer().getSeller(), new ArrayList<>());
                orderProducts.get(orderProduct.getOffer().getSeller()).add(orderProduct);

                this.cartProductService.deleteProduct(cartProduct);
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

    @Transactional
    @Override
    public OrderInfoDto updateOrderStatus(Long id, OrderStatus status) {
        var order=this.orderRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException(OBJECT_NAME_ORDER))
                .setStatus(status);

        this.orderRepository.save(order);
        return this.modelMapper.map(order, OrderInfoDto.class);
    }

    @Override
    public boolean isSeller(String name, Long id) {
        return this.orderRepository.findById(id)
                .orElseThrow(()->new ObjectNotFoundException(OBJECT_NAME_ORDER))
                .getSeller().getUsername()
                .equals(name);
    }
}
