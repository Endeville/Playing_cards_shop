package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.models.dtos.UserRegistrationDto;
import bg.softuni.playing_cards_shop.services.UserServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto initUser(){
        return new UserRegistrationDto();
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("form", "register");

        return "account";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDto user,
                           BindingResult result,
                           RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            return "redirect:/users/register";
        }

        this.userService.register(user);

        return "redirect:/decks/all";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("form", "login");

        return "account";
    }

    @PostMapping("/login-error")
    public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                              String username,
                              RedirectAttributes attributes){
        attributes
                .addFlashAttribute("badCredentials", true)
                .addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);

        return "redirect:/users/login";

    }

    @GetMapping("/profile/{username}")
    public String profile(@PathVariable(name="username") String username, Model model){
        var userProfile=this.userService.findUserProfileByUsername(username);

        model.addAttribute("user", userProfile);

        return "userProfile";
    }
}
