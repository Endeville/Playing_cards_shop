package bg.softuni.playing_cards_shop.web.interceptors;

import bg.softuni.playing_cards_shop.utils.ProfanityMap;
import bg.softuni.playing_cards_shop.web.exceptions.InappropriateLanguageException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Component
public class ProfanityInterceptor implements HandlerInterceptor {

    private final ProfanityMap profanityMap;

    public ProfanityInterceptor(ProfanityMap profanityMap) {
        this.profanityMap = profanityMap;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var params= request.getParameterMap().values().stream()
                .flatMap(Arrays::stream)
                .toList();

        if(!this.profanityMap.search(params)){
            throw new InappropriateLanguageException("Please refrain from using inappropriate language!");
        }

        return true;
    }
}
