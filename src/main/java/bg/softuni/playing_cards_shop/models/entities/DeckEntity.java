package bg.softuni.playing_cards_shop.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Set;

@Entity
@Table(name = "decks")
public class DeckEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String title;

    @Lob
    private String description;

    @OneToMany
    private Set<PictureEntity> pictures;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "country")
    private String countryOfOrigin;

    @ManyToOne(optional = false)
    private DistributorEntity distributor;

    @Column
    private String creator;
}
