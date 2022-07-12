package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.models.dtos.AddOfferDto;
import bg.softuni.playing_cards_shop.models.entities.enums.DeckCategory;
import bg.softuni.playing_cards_shop.services.interfaces.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
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
    public String addOfferView(){
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


}
