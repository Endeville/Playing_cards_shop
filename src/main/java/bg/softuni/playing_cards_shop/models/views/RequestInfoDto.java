package bg.softuni.playing_cards_shop.models.views;

public class RequestInfoDto {
    private Long id;
    private String content;
    private String creatorUsername;
    private boolean approved;


    public Long getId() {
        return id;
    }

    public RequestInfoDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public RequestInfoDto setContent(String content) {
        this.content = content;
        return this;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public RequestInfoDto setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public RequestInfoDto setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }
}
