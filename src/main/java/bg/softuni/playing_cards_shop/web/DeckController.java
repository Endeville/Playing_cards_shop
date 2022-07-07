package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.services.interfaces.DeckService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/decks")
public class DeckController {

    private final DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @ModelAttribute("search")
    public String search(){
        return "/decks";
    }


    @GetMapping("/all")
    public String catalog(Model model){
        model.addAttribute("decks",deckService.getApprovedDecks());

        return "catalog";
    }

    @GetMapping("/{id}")
    public String deckDetails(@PathVariable(name = "id") String stringId, Model model){
        var id=Long.parseLong(stringId);
        var deckDto=this.deckService.findDeckDetailsById(id);

        model.addAttribute("deck",deckDto);
        model.addAttribute("recommendedPrice", this.deckService.getRecommendedPriceForDeckWithId(id));

        return "deckDetails";
    }
}
