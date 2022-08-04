package bg.softuni.playing_cards_shop.web.rest;

import bg.softuni.playing_cards_shop.models.dtos.rest.UserUsernameDto;
import bg.softuni.playing_cards_shop.models.views.rest.UserPromotedDto;
import bg.softuni.playing_cards_shop.services.interfaces.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping(value = "/promote", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserPromotedDto> promote(@Valid @RequestBody UserUsernameDto userUsernameDto,
                                                   @AuthenticationPrincipal UserDetails principal){
        if (principal == null) {
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .header(HttpHeaders.LOCATION, "/users/login")
                    .build();
        }

        var result=this.userService.promote(userUsernameDto.getUsername());

        return ResponseEntity
                .ok(result);
    }
}
