package bg.softuni.playing_cards_shop.models.entities;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class AddressEntity  extends BaseEntity{

    @Column(nullable = false)
    private String address;

}
