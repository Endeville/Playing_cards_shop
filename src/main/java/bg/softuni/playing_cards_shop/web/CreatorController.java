package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.models.dtos.AddCreatorDto;
import bg.softuni.playing_cards_shop.services.interfaces.CreatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/creators")
public class CreatorController {
    private final CreatorService creatorService;

    public CreatorController(CreatorService creatorService) {
        this.creatorService = creatorService;
    }

    @ModelAttribute("creator")
    public AddCreatorDto addCreatorDto(){
        return new AddCreatorDto();
    }

    @GetMapping("/add")
    public String addCreatorForm(){
        return "addCreator";
    }

    @PostMapping("/add")
    public String addCreator(@Valid AddCreatorDto creatorDto,
                             BindingResult result,
                             RedirectAttributes attributes) throws IOException {
        if(result.hasErrors()){
            attributes.addFlashAttribute("creator", creatorDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.creator", result);

            return "redirect:/creators/add";
        }

        this.creatorService.addCreator(creatorDto);

        return "redirect:/decks/all";
    }

    @GetMapping("/{name}")
    public String creatorDetails(@PathVariable(name = "name") String name, Model model){
        var creator=this.creatorService.getCreatorDetailsByName(name);
        model.addAttribute("creator", creator);

        return "creatorDetails";
    }
}
