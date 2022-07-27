package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.models.dtos.AddOfferDto;
import bg.softuni.playing_cards_shop.models.dtos.EditOfferDto;
import bg.softuni.playing_cards_shop.models.views.ReviewDetailsDto;
import bg.softuni.playing_cards_shop.services.interfaces.DeckService;
import bg.softuni.playing_cards_shop.services.interfaces.OfferService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
                           RedirectAttributes attributes) throws IOException {
        if(result.hasErrors()){
            attributes.addFlashAttribute("offer", addOfferDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.offer", result);

            return "redirect:/offers/add";
        }

        offerService.addOffer(addOfferDto);

        return "redirect:/offers/all";
    }

    @GetMapping("/{id}")
    public String offerDetails(@PathVariable(name = "id") Long id,
                               Model model,
                               @AuthenticationPrincipal UserDetails principal){
        var offer=this.offerService.getOfferDetailsById(id);

        model.addAttribute("offer", offer);
//        model.addAttribute("canBuy", this.offerService.currentUserCanBuy(id));

        return "offerDetails";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable(name="id") Long id, Model model){
        var offerById = this.offerService.getOfferInfoById(id);

        model.addAttribute("offer", offerById);
        model.addAttribute("id", id);

        model.addAttribute("deckTitles", this.deckService.getAllDeckTitles());

        return "editOffer";
    }

    @PatchMapping("/{id}/edit")
    public String editOffer(@Valid EditOfferDto offer,
                            BindingResult result,
                            RedirectAttributes attributes,
                            @PathVariable(name="id") Long id) throws IOException {
        if(result.hasErrors()){
            attributes.addFlashAttribute("offer", offer);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.offer", result);

            return "redirect:/offers/" + id + "/edit";
        }

        this.offerService.editOffer(id,offer);

        return "redirect:/offers/all";
    }

    @GetMapping("/{id}/reviews")
    public String offerReviews(@PathVariable(name = "id") Long id, Model model){
        var offerReviews= this.offerService.findReviewsByOfferId(id);
        this.sortReviewInfo(offerReviews, model);

        model.addAttribute("reviews", offerReviews);

        return "reviews";
    }

    private void sortReviewInfo(List<ReviewDetailsDto> offerReviews, Model model) {
        model.addAttribute("ratings",
                offerReviews.stream()
                .collect(Collectors.groupingBy(ReviewDetailsDto::getRating, Collectors.counting())));

        model.addAttribute("averageRating",
                offerReviews.stream()
                        .mapToDouble(ReviewDetailsDto::getRating)
                        .average()
                        .orElse(0));
    }


}
