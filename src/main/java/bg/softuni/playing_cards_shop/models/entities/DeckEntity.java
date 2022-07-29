package bg.softuni.playing_cards_shop.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "decks")
public class DeckEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean approved;

    @Column
    private BigDecimal recommendedPrice;

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

    public boolean getApproved() {
        return approved;
    }

    public DeckEntity setApproved(boolean accepted) {
        this.approved = accepted;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeckEntity that = (DeckEntity) o;
        return approved == that.approved && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(pictures, that.pictures) && Objects.equals(countryOfOrigin, that.countryOfOrigin) && Objects.equals(distributor, that.distributor) && Objects.equals(creator, that.creator) && Objects.equals(categories, that.categories) && Objects.equals(offers, that.offers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, approved, pictures, countryOfOrigin, distributor, creator, categories, offers);
    }
}
