package talmo5.talmorello.card.service;


import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.card.constant.Priority;
import talmo5.talmorello.card.dto.CreateCardDTO;
import talmo5.talmorello.card.dto.CreateCardDTO.Response;
import talmo5.talmorello.card.dto.ModifyCardDateDTO;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.card.repository.CardRepository;
import talmo5.talmorello.carduser.entity.CardUser;
import talmo5.talmorello.carduser.repository.CardUserRepository;
import talmo5.talmorello.column.entity.Column;
import talmo5.talmorello.column.repository.ColumnRepository;
import talmo5.talmorello.global.exception.board.BoardNotFoundException;
import talmo5.talmorello.global.exception.card.AlreadyUserOfCardException;
import talmo5.talmorello.global.exception.card.CardNotFoundException;
import talmo5.talmorello.global.exception.column.ColumnNotFoundException;
import talmo5.talmorello.global.exception.column.InvalidNewOrdersException;
import talmo5.talmorello.global.exception.user.UserNotFoundException;
import talmo5.talmorello.user.entity.User;
import talmo5.talmorello.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    private final CardUserRepository cardUserRepository;

    //다른 도메인의 레포지토리는 추후 서비스로 변경될 가능성이 있음
    private final UserRepository userRepository;

    private final ColumnRepository columnRepository;

    public Response createCard(Long columnId, CreateCardDTO.Request createCardDTO, Long userId) {

        //boardId, columnId 다 받으며
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Column column = columnRepository.getColumnWithBoard(columnId)
                .orElseThrow(ColumnNotFoundException::new);
        Board board = column.getBoard();

        Long orders = getLastOrders(columnId);

        Card card = createCardDTO.toEntity(createCardDTO.cardTitle(), user,
                Math.toIntExact(orders + 1), column);

        cardRepository.save(card);

        return CreateCardDTO.Response.of(card);
    }

    private Long getLastOrders(Long columnId) {

        Long orders = cardRepository.getMaxOrderOfCardByColumnId(columnId);

        if(orders == null) return 0L;

        return orders;
    }

    @Transactional
    public void modifyCardTitle(Long cardId, String cardTitle, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        Board board = cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);

        card.changeCardTitle(cardTitle);
    }

    @Transactional
    public void modifyCardDescription(Long cardId, String cardDescription, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        Board board = cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);

        card.changeCardDescription(cardDescription);
    }

    @Transactional
    public void changeOrder(Long cardId, int newOrders, Long userId) {

        Card card = cardRepository.getCardWithColumn(cardId).orElseThrow(CardNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Board board = cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);
        Long maxOrders = cardRepository.getMaxOrderOfCardByColumnId(card.getColumn().getId());

        //현재 컬럼 내에서 유효한 오더인지 검사
        if(newOrders < 1 || newOrders > maxOrders) {
            throw new InvalidNewOrdersException();
        }

        cardRepository.changeOrders(cardId, card.getColumn().getId(), newOrders, card.getOrders());
    }

    @Transactional
    public void changeColumnOfCard(Long cardId, int cardOrders, Long columnId, Long userId) {

        //column 비교를 위해 fetch join
        Card card = cardRepository.getCardWithColumn(cardId).orElseThrow(CardNotFoundException::new);
        Column column = columnRepository.getColumnWithBoard(columnId)
                .orElseThrow(ColumnNotFoundException::new);
        Board board = cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        //내가 이동하려는 컬럼의 아이디가 현재 보드가 아니면?
        if(!column.getBoard().getId().equals(board.getId())) {
            throw new BoardNotFoundException();
        }

        //컬럼 변경하라고 했는데 같은 컬럼으로 변경한다고 하면 exception 던지기
        //추후 그냥 카드 순서 변경으로 되게끔 할 수도 있음 그래서 일단 에러코드 안 만들어씃ㅂ니ㅏㄷ..
        if(card.getColumn().getId().equals(columnId)) {
            throw new AlreadyUserOfCardException();
        }

        if(cardOrders < 1 || cardOrders > cardRepository.getMaxOrderOfCardByColumnId(columnId)) {
            throw new InvalidNewOrdersException();
        }

        cardRepository.changeColumnOfCard(cardOrders, cardId, column);
    }

    public void addUserToCard(Long cardId, Long userIdToAdd, Long userId) {

        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        User userToAdd = userRepository.findById(userIdToAdd).orElseThrow(UserNotFoundException::new);
        Board board = cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);

        verifyUserNotExistsInCard(cardId, userToAdd.getId());

        CardUser cardUser = CardUser.makeCardUser(card, userToAdd);

        cardUserRepository.save(cardUser);
    }

    private void verifyUserNotExistsInCard(Long cardId, Long userIdToAdd) {

        Optional<CardUser> cardUser = cardUserRepository.
                findCardUserByCardIdAndUserId(cardId, userIdToAdd);

        if(cardUser.isPresent()) {
            throw new AlreadyUserOfCardException();
        }
    }

    @Transactional
    public void deleteCardUser(Long cardId, Long userIdToDelete, Long userId) {

        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        User userToDelete = userRepository.findById(userIdToDelete)
                .orElseThrow(UserNotFoundException::new);
        Board board = cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);

        cardUserRepository.deleteCardUserByCardUserPK_CardAndCardUserPK_User(card, userToDelete);
    }

    @Transactional
    public void modifyCardDate(
            Long cardId, ModifyCardDateDTO modifyCardDateDTO, Long userId) {

        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Board board = cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);

        LocalDateTime startDate = modifyCardDateDTO.startDate();
        LocalDateTime dueDate = modifyCardDateDTO.dueDate();
        card.changeCardDate(startDate, dueDate);
    }

    @Transactional
    public void modifyCardPriority(Long cardId, Priority priority, Long userId) {

        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Board board = cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);

        card.changePriority(priority);
    }

    @Transactional
    public void deleteCard(Long cardId, Long userId) {

        Card card = cardRepository.getCardWithColumn(cardId).orElseThrow(CardNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Board board = cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);

        cardUserRepository.deleteAllCardUserByCardId(card.getId());

        cardRepository.deleteCard(card, card.getColumn());
    }
}
