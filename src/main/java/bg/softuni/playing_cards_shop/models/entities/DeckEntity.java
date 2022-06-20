package bg.softuni.playing_cards_shop.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "decks")
public class DeckEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, name = "accessible")
    private boolean accessible;

    @OneToMany
    @JoinColumn(name="deck_id")
    private Set<PictureEntity> pictures;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @ManyToOne(optional = false)
    private DistributorEntity distributor;

    @ManyToOne
    private CreatorEntity creator;

    @ManyToMany
    @JoinTable(name = "decks_categories")
    private Set<CategoryEntity> categories;

    @OneToMany(mappedBy = "deck")
    private Set<OfferEntity> offers;

    public DeckEntity() {
    }

    public String getTitle() {
        return title;
    }

    public DeckEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DeckEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getAccessible() {
        return accessible;
    }

    public DeckEntity setAccessible(Boolean accepted) {
        this.accessible = accepted;
        return this;
    }

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public DeckEntity setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public DeckEntity setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
        return this;
    }

    public DistributorEntity getDistributor() {
        return distributor;
    }

    public DeckEntity setDistributor(DistributorEntity distributor) {
        this.distributor = distributor;
        return this;
    }

    public CreatorEntity getCreator() {
        return creator;
    }

    public DeckEntity setCreator(CreatorEntity creator) {
        this.creator = creator;
        return this;
    }

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public DeckEntity setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
        return this;
    }

    public Set<OfferEntity> getOffers() {
        return offers;
    }

    public DeckEntity setOffers(Set<OfferEntity> offers) {
        this.offers = offers;
        return this;
    }
}
