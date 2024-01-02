package talmo5.talmorello.card.repository.custom;

import java.util.Optional;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.column.entity.Column;

public interface CustomCardRepository {

    void changeColumnOfCard(int order, Long cardId, Column column);

    Optional<Card> getCardWithColumn(Long cardId);

    Long getMaxOrderOfCardByColumnId(Long columnId);

    Optional<Board> getBoardByCardId(Long cardId);

    void subtractCardOrdersToDeleteCard(Card card, Column column);

    void changeOrders(Long cardId, Long columnId, int newOrders, int oldOrders);

    Optional<Card> getCardWithUserAndCommentListAndTodoList(Long cardId);
}
