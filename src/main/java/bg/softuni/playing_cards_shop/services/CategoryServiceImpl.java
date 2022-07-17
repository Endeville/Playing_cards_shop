package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.entities.CategoryEntity;
import bg.softuni.playing_cards_shop.models.entities.enums.DeckCategory;
import bg.softuni.playing_cards_shop.repositories.CategoryRepository;
import bg.softuni.playing_cards_shop.services.interfaces.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<CategoryEntity> findCategoriesByNames(List<String> categories) {
        return categories.stream()
                .map(c->this.categoryRepository.findCategoryEntityByCategory(DeckCategory.valueOf(c)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
