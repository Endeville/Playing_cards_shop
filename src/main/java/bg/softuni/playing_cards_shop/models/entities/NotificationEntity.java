package bg.softuni.playing_cards_shop.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name="notification")
public class NotificationEntity extends BaseEntity{

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private boolean seen;

    @Column(nullable = false)
    private LocalDate date;

    public String getContent() {
        return content;
    }

    public NotificationEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public boolean isSeen() {
        return seen;
    }

    public NotificationEntity setSeen(boolean seen) {
        this.seen = seen;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public NotificationEntity setDate(LocalDate date) {
        this.date = date;
        return this;
    }
}
