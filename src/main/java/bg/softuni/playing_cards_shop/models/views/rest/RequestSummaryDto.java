package bg.softuni.playing_cards_shop.models.views.rest;

public class RequestSummaryDto {
    private Long id;
    private String status;
    private String content;
    private String creatorUsername;

    public Long getId() {
        return id;
    }

    public RequestSummaryDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public RequestSummaryDto setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getContent() {
        return content;
    }

    public RequestSummaryDto setContent(String content) {
        this.content = content;
        return this;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public RequestSummaryDto setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
        return this;
    }
}
