package bg.softuni.playing_cards_shop.web.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class NotFoundAdvice {

    @ExceptionHandler({ObjectNotFoundException.class})
    public ModelAndView handleObjectNotFound(ObjectNotFoundException onfe){
        var mv=new ModelAndView("errors/objectNotFound");


        return mv;
    }
}
