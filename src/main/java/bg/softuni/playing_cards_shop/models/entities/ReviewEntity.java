package bg.softuni.playing_cards_shop.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name="reviews")
public class ReviewEntity extends BaseEntity {

    @Column(nullable = false)
    private String review;

    @Column(nullable = false)
    private short rating;

    @Column(nullable = false, name = "created_on")
    private Instant created;

    @ManyToOne
    private UserEntity creator;

    public String getReview() {
        return review;
    }

    public ReviewEntity setReview(String review) {
        this.review = review;
        return this;
    }

    public short getRating() {
        return rating;
    }

    public ReviewEntity setRating(short rating) {
        this.rating = rating;
        return this;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public ReviewEntity setCreator(UserEntity creator) {
        this.creator = creator;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public ReviewEntity setCreated(Instant created) {
        this.created = created;
        return this;
    }
}
