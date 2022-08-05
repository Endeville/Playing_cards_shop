package bg.softuni.playing_cards_shop.web.rest;

import bg.softuni.playing_cards_shop.models.dtos.rest.AddressIdDto;
import bg.softuni.playing_cards_shop.services.interfaces.AddressService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/addresses")
public class AddressRestController {

    private final AddressService addressService;

    public AddressRestController(AddressService addressService) {
        this.addressService = addressService;
    }


    @PreAuthorize("ownsAddress(#addressIdDto.id)")
    @DeleteMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> dislike(@Valid @RequestBody AddressIdDto addressIdDto, @AuthenticationPrincipal UserDetails principal) {
        if (principal == null) {
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .header(HttpHeaders.LOCATION, "/users/login")
                    .build();
        }

        this.addressService.deleteAddressById(addressIdDto.getId());

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
