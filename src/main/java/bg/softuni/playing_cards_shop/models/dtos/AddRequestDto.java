package bg.softuni.playing_cards_shop.models.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddRequestDto {
    @NotBlank(message = "Please make a valid request.")
    @Size(min=10, max = 1000, message = "The request should be more than 10 characters and up to 1000.")
    private String content;

    public String getContent() {
        return content;
    }

    public AddRequestDto setContent(String content) {
        this.content = content;
        return this;
    }
}
