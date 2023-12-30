package talmo5.talmorello.card.service;


import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import talmo5.talmorello.card.dto.CreateCardDTO;
import talmo5.talmorello.card.dto.CreateCardDTO.Request;
import talmo5.talmorello.card.dto.CreateCardDTO.Response;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.card.repository.CardRepository;
import talmo5.talmorello.carduser.entity.CardUser;
import talmo5.talmorello.carduser.repository.CardUserRepository;
import talmo5.talmorello.column.entity.Column;
import talmo5.talmorello.global.exception.card.AlreadyUserOfCardException;
import talmo5.talmorello.global.exception.card.CardNotFoundException;
import talmo5.talmorello.global.exception.column.InvalidNewOrdersException;
import talmo5.talmorello.user.entity.User;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    private final CardUserRepository cardUserRepository;

    private User tmpUser = User.builder()
            .id(1L)
            .email("wkdehdgk159@gmail.com")
            .username("dongha")
            .build();

    private Column tmpColumn = Column.builder()
            .id(2L)
            .title("임시 컬럼")
            .build();

    public Response createCard(Request createCardDTO, Long userId, Long columnId) {

        //userService 에서 userId에 해당하는 유저 받아오기
        //User user = userService.findUserById(userId);
        //Column column = columnService.findColumnById(columnId);

        Long orders = getLastOrders(columnId);

        Card card = createCardDTO.toEntity(createCardDTO.cardTitle(), tmpUser,
                Math.toIntExact(orders + 1), tmpColumn);

        cardRepository.save(card);

        return CreateCardDTO.Response.of(card);
    }

    private Long getLastOrders(Long columnId) {

        Long orders = cardRepository.getMaxOrderOfColumnByColumId(columnId);

        if(orders == null) return 0L;

        return orders;
    }

    @Transactional
    public void modifyCardTitle(Long cardId, String cardTitle) {

        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);

        card.changeCardTitle(cardTitle);
    }

    @Transactional
    public void modifyCardDescription(Long cardId, String cardDescription) {

        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);

        card.changeCardDescription(cardDescription);
    }

    @Transactional
    public void changeColumnOfCard(Long cardId, Long columnId, int cardOrders) {

//        Column column = columnService.findColumById(columnId);

        //column 비교를 위해 fetch join
        Card card = cardRepository.fetchJoinCard(cardId).orElseThrow(CardNotFoundException::new);

        //컬럼 변경하라고 했는데 같은 컬럼으로 변경한다고 하면 exception 던지기
        //추후 그냥 카드 순서 변경으로 되게끔 할 수도 있음 그래서 일단 에러코드 안 만들어씃ㅂ니ㅏㄷ..
        if(card.getColumn().getId().equals(columnId)) {
            throw new AlreadyUserOfCardException();
        }

        if(cardOrders < 1 || cardOrders > cardRepository.getMaxOrderOfColumnByColumId(columnId)) {
            throw new InvalidNewOrdersException();
        }

        cardRepository.changeColumnOfCard(cardOrders, cardId, tmpColumn);
    }

    public void addUserToCard(Long cardId, Long userId) {

        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        //User user = userService.findUserById(userId);
        verifyUserNotExistsInCard(cardId, tmpUser);

        CardUser cardUser = CardUser.makeCardUser(card, tmpUser);

        cardUserRepository.save(cardUser);
    }

    private void verifyUserNotExistsInCard(Long cardId, User user) {

        Optional<CardUser> cardUser = cardUserRepository.
                findCardUserByCardIdAndUserId(cardId, user.getId());

        if(cardUser.isPresent()) {
            throw new AlreadyUserOfCardException();
        }
    }

    public void deleteCardUser(Long cardId, Long userId) {

        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        //User user = userService.findUserById(userId);

        CardUser cardUser = CardUser.makeCardUser(card, tmpUser);

        cardUserRepository.delete(cardUser);
    }
}
