package bg.softuni.playing_cards_shop.services.schedulers;

import bg.softuni.playing_cards_shop.services.interfaces.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserRatingScheduler {

    private final UserService userService;


    public UserRatingScheduler(UserService userService) {
        this.userService = userService;
    }

    @Scheduled(cron = "* 8 12 * * *")
    public void updateUserRatings(){
        this.userService.updateRatings();
    }
}
