package bg.softuni.playing_cards_shop.web.rest;

import bg.softuni.playing_cards_shop.models.dtos.rest.DeckTitleDto;
import bg.softuni.playing_cards_shop.services.interfaces.DeckService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/decks")
public class DeckRestController {

    private final DeckService deckService;

    public DeckRestController(DeckService deckService) {
        this.deckService = deckService;
    }

    @DeleteMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> deleteDeck(@Valid @RequestBody DeckTitleDto deckTitleDto,
                                               @AuthenticationPrincipal UserDetails principal){
        if (principal == null) {
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .header(HttpHeaders.LOCATION, "/users/login")
                    .build();
        }

        this.deckService.deleteDeck(deckTitleDto);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
