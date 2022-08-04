package bg.softuni.playing_cards_shop.configs;

import bg.softuni.playing_cards_shop.web.interceptors.ProfanityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

private final ProfanityInterceptor profanityInterceptor;

    public WebConfiguration(ProfanityInterceptor profanityInterceptor) {
        this.profanityInterceptor = profanityInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.profanityInterceptor);
    }
}
