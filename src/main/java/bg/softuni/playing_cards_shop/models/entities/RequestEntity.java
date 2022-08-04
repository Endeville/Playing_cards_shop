package bg.softuni.playing_cards_shop.models.entities;



import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "requests")
public class RequestEntity extends BaseEntity{

    @Column(nullable = false)
    private Instant created;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(nullable = false)
    private UserEntity creator;

    public Instant getCreated() {
        return created;
    }

    public RequestEntity setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public String getContent() {
        return content;
    }

    public RequestEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public RequestEntity setCreator(UserEntity creator) {
        this.creator = creator;
        return this;
    }
}
