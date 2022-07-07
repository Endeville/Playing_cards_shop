package bg.softuni.playing_cards_shop.models.views;

public class CategoryDto {
    private String category;

    public String getCategory() {
        return category;
    }

    public CategoryDto setCategory(String category) {
        this.category = category;
        return this;
    }

    @Override
    public String toString() {
        return this.category;
    }
}
