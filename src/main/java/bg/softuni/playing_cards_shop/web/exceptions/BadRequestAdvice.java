package bg.softuni.playing_cards_shop.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class BadRequestAdvice {

    @ExceptionHandler(InappropriateLanguageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleObjectNotFound(InappropriateLanguageException ile){
        var mv=new ModelAndView("error/400");
        mv.addObject("reason", ile.getMessage());
        return mv;
    }
}
