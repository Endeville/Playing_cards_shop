package bg.softuni.playing_cards_shop.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "distributors")
public class DistributorEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String brand;

    @Column(unique = true, name = "url")
    private String siteUrl;

    @Column
    private String description;

    @OneToMany
    private Set<PictureEntity> pictures;

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

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public DistributorEntity setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }
}
