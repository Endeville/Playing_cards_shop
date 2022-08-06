package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.entities.CategoryEntity;
import bg.softuni.playing_cards_shop.models.entities.enums.DeckCategory;
import bg.softuni.playing_cards_shop.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setup(){
        categoryService=new CategoryServiceImpl(categoryRepository);
    }

    @Test
    public void testFindCategoriesByNames(){
        var categories= List.of("CARDISTRY", "GAFFED", "GAME");
        var search= List.of("GAME", "GAFFED");

        for (String category : categories) {
            lenient().when(categoryRepository.findCategoryEntityByCategory(DeckCategory.valueOf(category)))
                    .thenReturn(Optional.of(new CategoryEntity().setCategory(DeckCategory.valueOf(category))));
        }

        var result=categoryService.findCategoriesByNames(search);

        assertEquals(search.size(), result.size());

        for (int i = 0; i < search.size(); i++) {
            assertEquals(search.get(i), result.get(i).getCategory().name());
        }
    }
}
