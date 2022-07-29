package bg.softuni.playing_cards_shop.services.schedulers;

import bg.softuni.playing_cards_shop.services.interfaces.DeckService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RecommendedPricesScheduler {

    private final DeckService deckService;

    public RecommendedPricesScheduler(DeckService deckService) {
        this.deckService = deckService;
    }

    @Scheduled(cron = "* 0 2 * * *")
    public void updateRecommendedPrices(){
        this.deckService.updateRecommendedPrices();
    }
}
