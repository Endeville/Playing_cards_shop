package bg.softuni.playing_cards_shop.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ItemNotCreatedAdvice {

    @ExceptionHandler({ItemNotCreatedException.class})
    @ResponseStatus(value = HttpStatus.TEMPORARY_REDIRECT)
    public ModelAndView onItemNotCreated(ItemNotCreatedException ince){
        var mv=new ModelAndView("add" + ince.getObjectName().charAt(0) + ince.getObjectName().substring(1));
        mv.addObject("reason", "Could not find such " + ince.getObjectName());

        return mv;
    }
}
