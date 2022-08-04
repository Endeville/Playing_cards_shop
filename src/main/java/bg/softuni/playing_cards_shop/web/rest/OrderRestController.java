package bg.softuni.playing_cards_shop.web.rest;

import bg.softuni.playing_cards_shop.models.dtos.rest.OrderStatusDto;
import bg.softuni.playing_cards_shop.models.views.rest.OrderInfoDto;
import bg.softuni.playing_cards_shop.services.interfaces.OrderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("isSeller(#orderIdDto.id)")
    @PatchMapping(value="/updateStatus", consumes = "application/json", produces = "application/json")
    public ResponseEntity<OrderInfoDto> updateOrderStatus(@Valid @RequestBody OrderStatusDto orderIdDto,
                                                          @AuthenticationPrincipal UserDetails principal){
        if (principal == null) {
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .header(HttpHeaders.LOCATION, "/users/login")
                    .build();
        }

        var result=this.orderService.updateOrderStatus(orderIdDto.getId(), orderIdDto.getStatus());

        return ResponseEntity
                .ok()
                .body(result);
    }
}
