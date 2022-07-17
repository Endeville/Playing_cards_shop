package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.entities.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> findCategoriesByNames(List<String> categories);
}
