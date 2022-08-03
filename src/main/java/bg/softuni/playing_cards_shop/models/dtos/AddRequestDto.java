package bg.softuni.playing_cards_shop.models.dtos;

import javax.validation.constraints.NotBlank;

public class AddRequestDto {
    @NotBlank(message = "Please make a valid request.")
    private String content;

    public String getContent() {
        return content;
    }

    public AddRequestDto setContent(String content) {
        this.content = content;
        return this;
    }
}
