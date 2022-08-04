package bg.softuni.playing_cards_shop.services;

import bg.softuni.playing_cards_shop.models.dtos.AddRequestDto;
import bg.softuni.playing_cards_shop.models.entities.RequestEntity;
import bg.softuni.playing_cards_shop.models.views.RequestInfoDto;
import bg.softuni.playing_cards_shop.models.views.rest.RequestSummaryDto;
import bg.softuni.playing_cards_shop.repositories.RequestRepository;
import bg.softuni.playing_cards_shop.services.interfaces.RequestService;
import bg.softuni.playing_cards_shop.services.interfaces.UserService;
import bg.softuni.playing_cards_shop.web.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public RequestServiceImpl(RequestRepository requestRepository, UserService userService, ModelMapper modelMapper) {
        this.requestRepository = requestRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addRequest(AddRequestDto requestDto) {
        var request=new RequestEntity()
                .setCreated(Instant.now())
                .setContent(requestDto.getContent())
                .setCreator(this.userService.getCurrentUser());

        this.requestRepository.save(request);
    }

    @Override
    public List<RequestInfoDto> getAllRequests() {
        return this.requestRepository.findAll()
                .stream()
                .map(r->this.modelMapper.map(r, RequestInfoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRequest(Long id) {
        this.requestRepository.deleteById(id);
    }
}
