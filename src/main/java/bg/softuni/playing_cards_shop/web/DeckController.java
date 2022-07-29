package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.models.dtos.AddDeckDto;
import bg.softuni.playing_cards_shop.models.dtos.EditDeckDto;
import bg.softuni.playing_cards_shop.services.interfaces.CreatorService;
import bg.softuni.playing_cards_shop.services.interfaces.DeckService;
import bg.softuni.playing_cards_shop.services.interfaces.DistributorService;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.print.Pageable;
import java.io.IOException;

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

    @ModelAttribute("addDeck")
    public AddDeckDto addDeckDto(){
        return new AddDeckDto();
    }

    @ModelAttribute("editDeck")
    public EditDeckDto editDeckDto(){
        return new EditDeckDto();
    }


    @GetMapping("/all")
    public String catalog(@RequestParam(name="sort", required = false, defaultValue = "title") String sort,
                          @RequestParam(name = "search", required = false, defaultValue = "") String search,
                          Model model){
        model.addAttribute("decks",deckService.getApprovedDecksByKeyword(search, sort));
        model.addAttribute("showSearch", true);

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
                          RedirectAttributes attributes,
                          HttpServletRequest request) throws IOException {
        if(result.hasErrors()){
            attributes.addFlashAttribute("addDeck", addDeckDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.deck", result);

            return "redirect:/decks/add";
        }

        try {
            this.deckService.addDeck(addDeckDto);
        }catch(IllegalStateException e){
            attributes.addFlashAttribute("addDeck", addDeckDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.deck", result);

            return "redirect:/decks/add";
        }

        return "redirect:/decks/all";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable(name="id") Long id, Model model){
        var deckInfoById = this.deckService.findDeckInfoById(id);

        model.addAttribute("editDeck", deckInfoById);
        model.addAttribute("id", id);

        model.addAttribute("distributors", this.distributorService.getDistributorsNames());
        model.addAttribute("creators", this.creatorService.getCreatorsNames());

        return "editDeck";
    }

    @PatchMapping("/{id}/edit")
    public String editDeck(@Valid EditDeckDto editDeckDto,
                           BindingResult result,
                           RedirectAttributes attributes,
                           @PathVariable(name="id") Long id) throws IOException {
        if(result.hasErrors()){
            attributes.addFlashAttribute("editDeck", editDeckDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.deck", result);

            return "redirect:/decks/" + id + "/edit";
        }

        this.deckService.editDeck(id,editDeckDto);

        return "redirect:/decks/all";
    }
}
