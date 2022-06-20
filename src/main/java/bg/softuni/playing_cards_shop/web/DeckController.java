package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.services.interfaces.DeckService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catalog")
public class DeckController {

    private final DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }


    @GetMapping
    public String catalog(Model model){
        model.addAttribute("decks",deckService.getAccessibleDecks());

        return "catalog";
    }
}
