package bg.softuni.playing_cards_shop.configs;


import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ApplicationConfiguration {

    private final CloudConfiguration cloudConfiguration;

    public ApplicationConfiguration(CloudConfiguration cloudConfiguration) {
        this.cloudConfiguration = cloudConfiguration;
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(
                Map.of(
                        "cloud_name", cloudConfiguration.getCloudName(),
                        "api_key", cloudConfiguration.getApiKey(),
                        "api_secret", cloudConfiguration.getApiSecret()
                )
        );
    }
}
