package bg.softuni.playing_cards_shop.models.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "decks")
public class DeckEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private Integer recommendedPrice;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private PictureEntity picture;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @ManyToOne(optional = false, cascade = CascadeType.DETACH)
    private DistributorEntity distributor;

    @ManyToOne(cascade = CascadeType.DETACH)
    private CreatorEntity creator;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "decks_categories")
    private Set<CategoryEntity> categories;

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL)
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

    public PictureEntity getPicture() {
        return picture;
    }

    public DeckEntity setPicture(PictureEntity picture) {
        this.picture = picture;
        return this;
    }

    public Integer getRecommendedPrice() {
        return recommendedPrice;
    }

    public DeckEntity setRecommendedPrice(Integer recommendedPrice) {
        this.recommendedPrice = recommendedPrice;
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
        return Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(recommendedPrice, that.recommendedPrice) && Objects.equals(picture, that.picture) && Objects.equals(countryOfOrigin, that.countryOfOrigin) && Objects.equals(distributor, that.distributor) && Objects.equals(creator, that.creator) && Objects.equals(categories, that.categories) && Objects.equals(offers, that.offers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, recommendedPrice, picture, countryOfOrigin, distributor, creator, categories, offers);
    }
}
