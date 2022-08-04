package bg.softuni.playing_cards_shop.services.interfaces;

import bg.softuni.playing_cards_shop.models.dtos.AddRequestDto;
import bg.softuni.playing_cards_shop.models.views.RequestInfoDto;
import bg.softuni.playing_cards_shop.models.views.rest.RequestSummaryDto;

import java.util.List;

public interface RequestService {

    String OBJECT_NAME_REQUEST="request";

    void addRequest(AddRequestDto requestDto);

    List<RequestInfoDto> getAllRequests();

    void deleteRequest(Long id);
}
