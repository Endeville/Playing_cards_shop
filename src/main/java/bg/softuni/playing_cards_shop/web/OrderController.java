package bg.softuni.playing_cards_shop.web;

import bg.softuni.playing_cards_shop.models.dtos.CartNotesDto;
import bg.softuni.playing_cards_shop.services.interfaces.OrderService;
import bg.softuni.playing_cards_shop.web.exceptions.InvalidOrderException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
            return "redirect:/orders/placedOrders";
        }else{
            throw new InvalidOrderException("The cart is empty.", cartNotesDto);
        }
    }

    @ExceptionHandler(InvalidOrderException.class)
    public ModelAndView handleEmptyOrderExceptions(InvalidOrderException e) {
        var modelAndView = new ModelAndView("error/no-cart-products");
        modelAndView.addObject("warning", "Couldn't place the order because: " + e.getMessage());
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        return modelAndView;
    }

    @GetMapping("/placedOrders")
    public String getPlacedOrder(Model model){
        var placedOrders=this.orderService.findCurrentUserOrders();

        model.addAttribute("orders", placedOrders);

        return "orders";
    }

    @GetMapping("/myOrders")
    public String getMyOrders(Model model){
        var myOrders=this.orderService.findOrdersForCurrentUser();

        model.addAttribute("orders", myOrders);

        return "orders";
    }

    @GetMapping("/{id}")
    public String getOrderDetails(@PathVariable(name = "id") Long id, Model model){
        var orderDetails=this.orderService.findOrderDetailsById(id);

        model.addAttribute("order", orderDetails);

        return "orderDetails";
    }

}
