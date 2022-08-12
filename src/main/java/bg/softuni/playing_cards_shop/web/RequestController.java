package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.models.dtos.AddRequestDto;
import bg.softuni.playing_cards_shop.services.events.SendRequestEvent;
import bg.softuni.playing_cards_shop.services.interfaces.RequestService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/requests")
public class RequestController {

    private final ApplicationEventPublisher eventPublisher;
    private final RequestService requestService;

    public RequestController(ApplicationEventPublisher eventPublisher, RequestService requestService) {
        this.eventPublisher = eventPublisher;
        this.requestService = requestService;
    }


    @ModelAttribute("addRequest")
    public AddRequestDto addRequestDto() {
        return new AddRequestDto();
    }

    @GetMapping("/add")
    public String getAddRequestPage() {
        return "addRequest";
    }

    @PostMapping("/add")
    public String addRequest(@Valid AddRequestDto requestDto,
                             BindingResult result,
                             RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("addRequest", requestDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.addRequest", result);

            return "redirect:/requests/add";
        }
        this.requestService.addRequest(requestDto);

        return "redirect:/decks/all";
    }

    @GetMapping("/all")
    public String allRequest(Model model) {
        var requests = this.requestService.getAllRequests();

        model.addAttribute("requests", requests);

        return "requests";
    }
}
