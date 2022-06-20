package bg.softuni.playing_cards_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("java/bg/softuni/playing_cards_shop/models/entities")
@SpringBootApplication
public class PlayingCardsShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayingCardsShopApplication.class, args);
    }

}
