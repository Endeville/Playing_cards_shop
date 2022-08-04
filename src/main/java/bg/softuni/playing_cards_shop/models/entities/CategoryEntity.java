package bg.softuni.playing_cards_shop.models.entities;

import bg.softuni.playing_cards_shop.models.entities.enums.DeckCategory;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private DeckCategory category;

    @ManyToMany(mappedBy = "categories")
    private Set<DeckEntity> decks;

    public CategoryEntity() {
    }

    public DeckCategory getCategory() {
        return category;
    }

    public CategoryEntity setCategory(DeckCategory category) {
        this.category = category;
        return this;
    }
}
