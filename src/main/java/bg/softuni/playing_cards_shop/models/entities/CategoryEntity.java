package bg.softuni.playing_cards_shop.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{

    @Column
    private String category;

    public CategoryEntity() {
    }

    public String getCategory() {
        return category;
    }

    public CategoryEntity setCategory(String category) {
        this.category = category;
        return this;
    }
}
