package bg.softuni.playing_cards_shop.models.entities;


import bg.softuni.playing_cards_shop.models.entities.enums.RequestStatus;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "requests")
public class RequestEntity extends BaseEntity{

    @Column(nullable = false)
    private Instant created;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus processed;

    @Column(nullable = false)
    private String content;

    public Instant getCreated() {
        return created;
    }

    public RequestEntity setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public RequestStatus getProcessed() {
        return processed;
    }

    public RequestEntity setProcessed(RequestStatus processed) {
        this.processed = processed;
        return this;
    }

    public String getContent() {
        return content;
    }

    public RequestEntity setContent(String content) {
        this.content = content;
        return this;
    }
}
