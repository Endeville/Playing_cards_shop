package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.models.dtos.AddDeckDto;
import bg.softuni.playing_cards_shop.services.interfaces.CreatorService;
import bg.softuni.playing_cards_shop.services.interfaces.DeckService;
import bg.softuni.playing_cards_shop.services.interfaces.DistributorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/decks")
public class DeckController {

    private final DeckService deckService;
    private final DistributorService distributorService;
    private final CreatorService creatorService;

    public DeckController(DeckService deckService, DistributorService distributorService, CreatorService creatorService) {
        this.deckService = deckService;
        this.distributorService = distributorService;
        this.creatorService = creatorService;
    }

    @ModelAttribute("deck")
    public AddDeckDto addDeckDto(){
        return new AddDeckDto();
    }


    @GetMapping("/all")
    public String catalog(Model model){
        model.addAttribute("decks",deckService.getApprovedDecks());

        return "catalog";
    }

    @GetMapping("/{id}")
    public String deckDetails(@PathVariable(name = "id") Long id, Model model){
        var deckDto=this.deckService.findDeckDetailsById(id);

        model.addAttribute("deck",deckDto);
        model.addAttribute("recommendedPrice", this.deckService.getRecommendedPriceForDeckWithId(id));

        return "deckDetails";
    }

    @GetMapping("/add")
    public String addDeckForm(Model model){
        model.addAttribute("distributors", this.distributorService.getDistributorsNames());
        model.addAttribute("creators", this.creatorService.getCreatorsNames());

        return "addDeck";
    }

    @PostMapping("/add")
    public String addDeck(@Valid AddDeckDto addDeckDto,
                          BindingResult result,
                          RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("deck", addDeckDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.deck", result);

            return "redirect:/decks/add";
        }

        this.deckService.addDeck(addDeckDto);


        //todo: reconsider
        return "redirect:/decks/all";
    }
}
