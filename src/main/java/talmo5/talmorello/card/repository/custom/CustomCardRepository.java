package talmo5.talmorello.card.repository.custom;

import java.util.Optional;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.column.entity.Column;

public interface CustomCardRepository {

    public void changeColumnOfCard(int order, Long cardId, Column column);

    public Optional<Card> getCardWithColumn(Long cardId);

    public Long getMaxOrderOfCardByColumnId(Long columnId);

    public Optional<Board> getBoardByCardId(Long cardId);
}
