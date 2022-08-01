package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.models.dtos.CartNotesDto;
import bg.softuni.playing_cards_shop.services.interfaces.OrderService;
import bg.softuni.playing_cards_shop.web.exceptions.InvalidOrderException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ModelAttribute("cartNotes")
    public CartNotesDto cartNotesDto(){
        return new CartNotesDto();
    }

    @PostMapping("/add")
    public String addOrder(@Valid CartNotesDto cartNotesDto,
                           BindingResult result,
                           RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("cartNotes", cartNotesDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.cartNotes", result);

            return "redirect:/cart";
        }

        if(this.orderService.placeOrder(cartNotesDto)){
            return "redirect:/orders";
        }else{
            throw new InvalidOrderException("The cart is empty", cartNotesDto);
        }
    }

    @ExceptionHandler(InvalidOrderException.class)
    public ModelAndView handleEmptyOrderExceptions(InvalidOrderException e) {
        ModelAndView modelAndView = new ModelAndView("/cart");
        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("cartNotes", e.getCartNotesDto());
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        return modelAndView;
    }
}
