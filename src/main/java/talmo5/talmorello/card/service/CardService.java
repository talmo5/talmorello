package talmo5.talmorello.card.service;


import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.boarduser.validator.BoardUserValidator;
import talmo5.talmorello.card.constant.Priority;
import talmo5.talmorello.card.dto.CreateCardDTO;
import talmo5.talmorello.card.dto.CreateCardDTO.Response;
import talmo5.talmorello.card.dto.GetCardDTO;
import talmo5.talmorello.card.dto.ModifyCardDateDTO;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.card.repository.CardRepository;
import talmo5.talmorello.carduser.entity.CardUser;
import talmo5.talmorello.carduser.repository.CardUserRepository;
import talmo5.talmorello.column.entity.Column;
import talmo5.talmorello.column.service.ColumnService;
import talmo5.talmorello.global.exception.board.BoardNotFoundException;
import talmo5.talmorello.global.exception.card.AlreadyUserOfCardException;
import talmo5.talmorello.global.exception.card.CardNotFoundException;
import talmo5.talmorello.global.exception.column.InvalidNewOrdersException;
import talmo5.talmorello.user.entity.User;
import talmo5.talmorello.user.service.UserService;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    private final CardUserRepository cardUserRepository;

    private final BoardUserValidator boardUserValidator;

    private final UserService userService;

    private final ColumnService columnService;

    public Response createCard(Long columnId, CreateCardDTO.Request createCardDTO, Long userId) {

        User user = userService.findById(userId);
        Column column = columnService.getColumnWithBoard(columnId);

        boardUserValidator.validateBoardUser(column.getBoard(), user);

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

    public GetCardDTO getCard(Long cardId, Long userId) {

        User user = userService.findById(userId);
        Card card = cardRepository.getCardWithUserAndCommentListAndTodoList(cardId)
                .orElseThrow(CardNotFoundException::new);
        Board board = cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);

        boardUserValidator.validateBoardUser(board, user);

        return GetCardDTO.from(card);
    }

    @Transactional
    public void modifyCardTitle(Long cardId, String cardTitle, Long userId) {

        User user = userService.findById(userId);
        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        Board board = cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);

        boardUserValidator.validateBoardUser(board, user);

        card.changeCardTitle(cardTitle);
    }

    @Transactional
    public void modifyCardDescription(Long cardId, String cardDescription, Long userId) {

        User user = userService.findById(userId);
        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        Board board = cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);

        boardUserValidator.validateBoardUser(board, user);

        card.changeCardDescription(cardDescription);
    }

    @Transactional
    public void changeOrder(Long cardId, int newOrders, Long userId) {

        Card card = cardRepository.getCardWithColumn(cardId).orElseThrow(CardNotFoundException::new);
        User user = userService.findById(userId);
        Board board = cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);

        boardUserValidator.validateBoardUser(board, user);

        Long maxOrders = cardRepository.getMaxOrderOfCardByColumnId(card.getColumn().getId());

        if(newOrders < 1 || newOrders > maxOrders) {
            throw new InvalidNewOrdersException();
        }

        cardRepository.changeOrders(cardId, card.getColumn().getId(), newOrders, card.getOrders());
    }

    @Transactional
    public void changeColumnOfCard(Long cardId, int cardOrders, Long columnId, Long userId) {

        Card card = cardRepository.getCardWithColumn(cardId).orElseThrow(CardNotFoundException::new);
        Column newColumn = columnService.getColumnWithBoard(columnId);
        Column oldColumn = card.getColumn();
        Board board = cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);
        User user = userService.findById(userId);

        boardUserValidator.validateBoardUser(board, user);

        validateChangeColumnOfCard(board, newColumn, card, columnId, cardOrders);

        cardRepository.changeColumnOfCard(cardOrders, card, newColumn, oldColumn);
    }

    private void validateChangeColumnOfCard(Board board, Column column, Card card, Long columnId, int cardOrders) {
        if(!column.getBoard().getId().equals(board.getId())) {
            throw new BoardNotFoundException();
        }

        if(card.getColumn().getId().equals(columnId)) {
            throw new AlreadyUserOfCardException();
        }

        if(cardOrders < 1 || cardOrders > cardRepository.getMaxOrderOfCardByColumnId(columnId)) {
            throw new InvalidNewOrdersException();
        }
    }

    public void addUserToCard(Long cardId, Long userIdToAdd, Long userId) {

        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        User user = userService.findById(userId);
        User userToAdd = userService.findById(userIdToAdd);
        Board board = cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);

        boardUserValidator.validateBoardUser(board, user);
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
        User user = userService.findById(userId);
        User userToDelete = userService.findById(userIdToDelete);
        Board board = cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);

        boardUserValidator.validateBoardUser(board, user);

        cardUserRepository.deleteCardUserByCardUserPK_CardAndCardUserPK_User(card, userToDelete);
    }

    @Transactional
    public void modifyCardDate(
            Long cardId, ModifyCardDateDTO modifyCardDateDTO, Long userId) {

        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        User user = userService.findById(userId);
        Board board = cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);

        boardUserValidator.validateBoardUser(board, user);

        LocalDateTime startDate = modifyCardDateDTO.startDate();
        LocalDateTime dueDate = modifyCardDateDTO.dueDate();
        card.changeCardDate(startDate, dueDate);
    }

    @Transactional
    public void modifyCardPriority(Long cardId, Priority priority, Long userId) {

        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        User user = userService.findById(userId);
        Board board = cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);

        boardUserValidator.validateBoardUser(board, user);

        card.changePriority(priority);
    }

    @Transactional
    public void deleteCard(Long cardId, Long userId) {

        Card card = cardRepository.getCardWithColumn(cardId).orElseThrow(CardNotFoundException::new);
        User user = userService.findById(userId);
        Board board = cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);

        boardUserValidator.validateBoardUser(board, user);

        cardUserRepository.deleteAllCardUserByCardId(card.getId());
        cardRepository.subtractCardOrdersToDeleteCard(card, card.getColumn());

        cardRepository.delete(card);
    }

    public Card findById(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
    }

    public Board getBoardByCardId(Long cardId) {
        return cardRepository.getBoardByCardId(cardId).orElseThrow(BoardNotFoundException::new);
    }
}
