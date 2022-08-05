package bg.softuni.playing_cards_shop.web.rest;

import bg.softuni.playing_cards_shop.models.dtos.rest.DeckTitleDto;
import bg.softuni.playing_cards_shop.models.dtos.rest.OfferIdDto;
import bg.softuni.playing_cards_shop.services.interfaces.OfferService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/offers")
public class OfferRestController {

    private final OfferService offerService;

    public OfferRestController(OfferService offerService) {
        this.offerService = offerService;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') or isOwner(#offerIdDto.id)")
    @DeleteMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> deleteOffer(@Valid @RequestBody OfferIdDto offerIdDto,
                                               @AuthenticationPrincipal UserDetails principal){
        if (principal == null) {
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .header(HttpHeaders.LOCATION, "/users/login")
                    .build();
        }

        this.offerService.deleteOffer(offerIdDto.getId());

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
