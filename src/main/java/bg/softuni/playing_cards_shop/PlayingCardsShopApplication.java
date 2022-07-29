package bg.softuni.playing_cards_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PlayingCardsShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayingCardsShopApplication.class, args);
    }

}
