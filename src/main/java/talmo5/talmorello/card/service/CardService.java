package talmo5.talmorello.card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talmo5.talmorello.card.dto.CreateCardDTO;
import talmo5.talmorello.card.dto.CreateCardDTO.Request;
import talmo5.talmorello.card.dto.CreateCardDTO.Response;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.card.repository.CardRepository;
import talmo5.talmorello.user.entity.User;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    private User user = User.builder()
            .id(1L)
            .email("wkdehdgk159@gmail.com")
            .username("dongha")
            .build();

    public Response createCard(Request createCardDTO, Long userId) {

        //userService 에서 userId에 해당하는 유저 받아오기
        //User user = userService.findUserById(userId);

        int orders = cardRepository.getLastOrders();

        Card card = createCardDTO.toEntity(createCardDTO.cardTitle(), user, orders + 1);

        cardRepository.save(card);

        return CreateCardDTO.Response.of(card);
    }
}
