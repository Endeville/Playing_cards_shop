package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.models.dtos.AddDistributorDto;
import bg.softuni.playing_cards_shop.services.interfaces.DistributorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/distributors")
public class DistributorController {

    private final DistributorService distributorService;

    public DistributorController(DistributorService distributorService) {
        this.distributorService = distributorService;
    }

    @ModelAttribute("distributor")
    public AddDistributorDto distributor(){
        return new AddDistributorDto();
    }

    @GetMapping("/{id}")
    public String distributorDetails(@PathVariable(name = "id") Long id, Model model){
        var distributorDto=distributorService.findDistributorById(id);

        model.addAttribute("distributor", distributorDto);

        return "distributorDetails";
    }

    @GetMapping("/add")
    public String addDistributor(){
        return "addDistributor";
    }


    @PostMapping("/add")
    public String addDistributor(@Valid AddDistributorDto distributorDto,
                                 BindingResult result,
                                 RedirectAttributes attributes) throws IOException {
        if(result.hasErrors()){
            attributes.addFlashAttribute("distributor", distributorDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.distributor", result);

            return "redirect:/distributors/add";
        }

        this.distributorService.addDistributor(distributorDto);

        //todo: reconsider
        return "redirect:/decks/all";
    }
}
