package bg.softuni.playing_cards_shop.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class NotFoundAdvice {

    @ExceptionHandler({ObjectNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleObjectNotFound(ObjectNotFoundException onfe){
        var mv=new ModelAndView("error/404");
        mv.addObject("reason", onfe.getObjectName());
        return mv;
    }
}
