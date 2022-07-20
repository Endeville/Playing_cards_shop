package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.models.dtos.AddOfferDto;
import bg.softuni.playing_cards_shop.services.interfaces.DeckService;
import bg.softuni.playing_cards_shop.services.interfaces.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final DeckService deckService;

    public OfferController(OfferService offerService, DeckService deckService) {
        this.offerService = offerService;
        this.deckService = deckService;
    }

    @ModelAttribute("offer")
    public AddOfferDto addOfferDto(){
        return new AddOfferDto();
    }

    @GetMapping("/all")
    public String showOffers(Model model){
        var offers=this.offerService.getActiveOffers();
        model.addAttribute("offers", offers);

        return "offers";
    }

    @GetMapping("/add")
    public String addOfferView(Model model){

        model.addAttribute("deckTitles", this.deckService.getAllDeckTitles());

        return "addOffer";
    }

    @PostMapping("/add")
    public String addOffer(@Valid AddOfferDto addOfferDto,
                           BindingResult result,
                           RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("offer", addOfferDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.offer", result);

            return "redirect:/offers/add";
        }

        offerService.addOffer(addOfferDto);

        return "redirect:/offers/all";
    }

    @GetMapping("/{id}")
    public String offerDetails(@PathVariable(name = "id") Long id, Model model){
        var offer=this.offerService.getOfferDetailsById(id);
        model.addAttribute("offer", offer);

        return "offerDetails";
    }


}
