package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.models.dtos.AddDeckDto;
import bg.softuni.playing_cards_shop.models.dtos.EditDeckDto;
import bg.softuni.playing_cards_shop.services.interfaces.CreatorService;
import bg.softuni.playing_cards_shop.services.interfaces.DeckService;
import bg.softuni.playing_cards_shop.services.interfaces.DistributorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;

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
    public AddDeckDto addDeckDto() {
        return new AddDeckDto();
    }

    @ModelAttribute("editDeck")
    public EditDeckDto editDeckDto() {
        return new EditDeckDto();
    }


    @GetMapping("/all")
    public String catalog(@RequestParam(name = "sort", required = false, defaultValue = "title") String sort,
                          @RequestParam(name = "search", required = false, defaultValue = "") String search,
                          @RequestParam(name = "distributor", required = false, defaultValue = "") String distributor,
                          @RequestParam(name = "creator", required = false, defaultValue = "") String creator,
                          Model model) {
        model.addAttribute("decks", deckService.getDecksByKeyword(search, sort, distributor, creator));
        model.addAttribute("showSearch", true);

        return "catalog";
    }

    @GetMapping("/{id}")
    public String deckDetails(@PathVariable(name = "id") Long id, Model model) {
        var deckDto = this.deckService.findDeckDetailsById(id);

        model.addAttribute("deck", deckDto);

        return "deckDetails";
    }

    @GetMapping("/add")
    public String addDeckForm(Model model) {
        model.addAttribute("distributors", this.distributorService.getDistributorsNames());
        model.addAttribute("creators", this.creatorService.getCreatorsNames());

        return "addDeck";
    }

    @PostMapping("/add")
    public String addDeck(@Valid AddDeckDto addDeckDto,
                          BindingResult result,
                          RedirectAttributes attributes) throws IOException {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("addDeck", addDeckDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.addDeck", result);

            return "redirect:/decks/add";
        }

        try {
            this.deckService.addDeck(addDeckDto);
        } catch (IllegalStateException e) {
            attributes.addFlashAttribute("addDeck", addDeckDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.addDeck", result);

            return "redirect:/decks/add";
        }

        return "redirect:/decks/all";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable(name = "id") Long id, Model model) {
        var deckInfoById = this.deckService.findDeckInfoById(id);

        if(model.asMap().get("org.springframework.validation.BindingResult.editOffer")==null){
            model.addAttribute("editDeck", deckInfoById);
        }

        model.addAttribute("id", id);

        model.addAttribute("distributors", this.distributorService.getDistributorsNames());
        model.addAttribute("creators", this.creatorService.getCreatorsNames());

        return "editDeck";
    }

    @PatchMapping("/edit/{id}")
    public String editDeck(@Valid EditDeckDto editDeckDto,
                           BindingResult result,
                           RedirectAttributes attributes,
                           @PathVariable(name = "id") Long id){
        if (result.hasErrors()) {
            if (Objects.equals(editDeckDto.getPicture().getOriginalFilename(), "")) {
                editDeckDto.setPicture(null);
            }
            attributes.addFlashAttribute("editDeck", editDeckDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.editDeck", result);

            return "redirect:/decks/edit/" + id + "/error";
        }

        return "redirect:/decks/all";
    }
}
