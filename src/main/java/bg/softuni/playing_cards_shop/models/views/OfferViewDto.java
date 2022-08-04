package bg.softuni.playing_cards_shop.models.views;

import java.math.BigDecimal;

public class OfferViewDto {
    private Long id;
    private String deckTitle;
    private String sellerUsername;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public OfferViewDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDeckTitle() {
        return deckTitle;
    }

    public OfferViewDto setDeckTitle(String deckTitle) {
        this.deckTitle = deckTitle;
        return this;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public OfferViewDto setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferViewDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
