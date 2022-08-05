package bg.softuni.playing_cards_shop.web.rest;

import bg.softuni.playing_cards_shop.models.dtos.rest.RequestDeleteDto;
import bg.softuni.playing_cards_shop.services.interfaces.RequestService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/requests")
public class RequestRestController {

    private final RequestService requestService;

    public RequestRestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value="/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> checkRequest(@Valid @RequestBody RequestDeleteDto requestStatusDto,
                                                               @AuthenticationPrincipal UserDetails principal){
        if (principal == null) {
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .header(HttpHeaders.LOCATION, "/users/login")
                    .build();
        }

        this.requestService.deleteRequest(requestStatusDto.getId());

        return ResponseEntity
                .ok()
                .build();
    }
}
