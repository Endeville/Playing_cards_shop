package bg.softuni.playing_cards_shop.services.events;

import bg.softuni.playing_cards_shop.models.entities.RequestEntity;
import bg.softuni.playing_cards_shop.models.entities.enums.RequestStatus;
import bg.softuni.playing_cards_shop.repositories.RequestRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class SendRequestListener implements ApplicationListener<SendRequestEvent> {

    private final RequestRepository requestRepository;

    public SendRequestListener(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public void onApplicationEvent(SendRequestEvent event) {
        var request=new RequestEntity()
                .setCreated(Instant.now())
                .setProcessed(RequestStatus.PENDING)
                .setContent(event.getContent());

        this.requestRepository.save(request);
    }
}
