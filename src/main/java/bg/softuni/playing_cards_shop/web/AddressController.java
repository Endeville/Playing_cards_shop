package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.models.dtos.AddressAddDto;
import bg.softuni.playing_cards_shop.services.interfaces.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @ModelAttribute("address")
    public AddressAddDto address(){
        return new AddressAddDto();
    }

    @GetMapping("/add")
    public String addAddressPage(){
        return "addAddress";
    }

    @PostMapping("/add")
    public String addAddress(@Valid AddressAddDto address,
                             BindingResult result,
                             RedirectAttributes attributes,
                             Principal principal){
        if(result.hasErrors()){
            attributes.addFlashAttribute("address", address);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.address", result);

            return "redirect:/addresses/add";
        }

        this.addressService.addAddress(address, principal.getName());

        return "redirect:/users/profile/" + principal.getName();
    }
}
