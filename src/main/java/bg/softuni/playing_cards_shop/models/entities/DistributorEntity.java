package bg.softuni.playing_cards_shop.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "distributors")
public class DistributorEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String brand;

    @Column(unique = true, name = "url")
    private String siteUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne
    private PictureEntity picture;

    @OneToMany(mappedBy = "distributor")
    private Set<DeckEntity> decks;

    public DistributorEntity() {
    }

    public String getBrand() {
        return brand;
    }

    public DistributorEntity setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DistributorEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public DistributorEntity setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
        return this;
    }

    public PictureEntity getPicture() {
        return picture;
    }

    public DistributorEntity setPicture(PictureEntity picture) {
        this.picture = picture;
        return this;
    }

    public Set<DeckEntity> getDecks() {
        return decks;
    }

    public DistributorEntity setDecks(Set<DeckEntity> decks) {
        this.decks = decks;
        return this;
    }
}
