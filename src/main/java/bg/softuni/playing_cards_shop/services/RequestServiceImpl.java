package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.AddRequestDto;
import bg.softuni.playing_cards_shop.models.entities.RequestEntity;
import bg.softuni.playing_cards_shop.models.entities.enums.RequestStatus;
import bg.softuni.playing_cards_shop.repositories.RequestRepository;
import bg.softuni.playing_cards_shop.services.interfaces.RequestService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final ModelMapper modelMapper;

    public RequestServiceImpl(RequestRepository requestRepository, ModelMapper modelMapper) {
        this.requestRepository = requestRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addRequest(AddRequestDto requestDto) {
        var request=new RequestEntity()
                .setCreated(Instant.now())
                .setProcessed(RequestStatus.PENDING)
                .setContent(requestDto.getContent());

        this.requestRepository.save(request);
    }
}
