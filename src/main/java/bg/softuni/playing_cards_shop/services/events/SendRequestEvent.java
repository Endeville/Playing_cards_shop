package bg.softuni.playing_cards_shop.services.events;

import org.springframework.context.ApplicationEvent;

public class SendRequestEvent extends ApplicationEvent {

    private String content;

    public SendRequestEvent(Object source, String content) {
        super(source);
        this.content=content;
    }

    public String getContent() {
        return content;
    }

    public SendRequestEvent setContent(String content) {
        this.content = content;
        return this;
    }
}
