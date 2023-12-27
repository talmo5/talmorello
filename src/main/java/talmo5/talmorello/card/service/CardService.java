package talmo5.talmorello.card.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talmo5.talmorello.card.dto.CardMemberDTO;
import talmo5.talmorello.card.dto.CreateCardDTO;
import talmo5.talmorello.card.dto.CreateCardDTO.Request;
import talmo5.talmorello.card.dto.CreateCardDTO.Response;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.card.repository.CardRepository;
import talmo5.talmorello.carduser.entity.CardUser;
import talmo5.talmorello.carduser.pk.CardUserPK;
import talmo5.talmorello.carduser.repository.CardUserRepository;
import talmo5.talmorello.global.exception.card.AlreadyMemberOfCardException;
import talmo5.talmorello.global.exception.card.CardNotFoundException;
import talmo5.talmorello.global.exception.card.NotMemberOfCardException;
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

    public Response createCard(Request createCardDTO, Long userId) {

        //userService 에서 userId에 해당하는 유저 받아오기
        //User user = userService.findUserById(userId);

        int orders = getLastOrders();

        Card card = createCardDTO.toEntity(createCardDTO.cardTitle(), tmpUser, orders + 1);

        cardRepository.save(card);

        return CreateCardDTO.Response.of(card);
    }

    private int getLastOrders() {

        Integer orders = cardRepository.getLastOrders();

        if(orders == null) return 0;

        return orders;
    }

    public CardMemberDTO addMemberToCard(Long cardId, Long userId) {

        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        //User user = userService.findUserById(userId);
        verifyUserNotExistsInCard(cardId, tmpUser);

        saveCardUser(card, tmpUser);

        List<User> userList = cardUserRepository.findUserByCardId(cardId);

        return CardMemberDTO.of(userList);
    }

    private void verifyUserNotExistsInCard(Long cardId, User user) {

        List<User> userList = cardUserRepository.findUserByCardId(cardId);
        List<Long> userIdList = userList.stream().map(User::getId).toList();

        if(userIdList.contains(tmpUser.getId())) {
            throw new AlreadyMemberOfCardException();
        }
    }

    private void saveCardUser(Card card, User user) {

        CardUserPK cardUserPK = CardUserPK.builder()
                .user(tmpUser)
                .card(card)
                .build();

        CardUser cardUser = CardUser.builder()
                .cardUserPK(cardUserPK)
                .build();

        cardUserRepository.save(cardUser);
    }

    public CardMemberDTO deleteCardMember(Long cardId, Long userId) {

        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        //User user = userService.findUserById(userId);
        verifyUserExistsInCard(cardId, tmpUser);

        deleteCardUser(card, tmpUser);

        List<User> userList = cardUserRepository.findUserByCardId(cardId);

        return CardMemberDTO.of(userList);
    }

    private void verifyUserExistsInCard(Long cardId, User user) {

        List<User> userList = cardUserRepository.findUserByCardId(cardId);
        List<Long> userIdList = userList.stream().map(User::getId).toList();

        if(!userIdList.contains(tmpUser.getId())) {
            throw new NotMemberOfCardException();
        }
    }

    private void deleteCardUser(Card card, User user) {

        CardUserPK cardUserPK = CardUserPK.builder()
                .user(tmpUser)
                .card(card)
                .build();

        CardUser cardUser = CardUser.builder()
                .cardUserPK(cardUserPK)
                .build();

        cardUserRepository.delete(cardUser);
    }
}
