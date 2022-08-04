package bg.softuni.playing_cards_shop.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "creators")
public class CreatorEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "creator")
    private Set<DeckEntity> decks;

    @OneToOne
    private PictureEntity picture;

    public CreatorEntity() {
    }

    public String getName() {
        return name;
    }

    public CreatorEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreatorEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<DeckEntity> getDecks() {
        return decks;
    }

    public CreatorEntity setDecks(Set<DeckEntity> decks) {
        this.decks = decks;
        return this;
    }

    public PictureEntity getPicture() {
        return picture;
    }

    public CreatorEntity setPicture(PictureEntity picture) {
        this.picture = picture;
        return this;
    }
}
