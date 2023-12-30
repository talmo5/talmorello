package talmo5.talmorello.card.repository.custom;

import java.util.Optional;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.column.entity.Column;

public interface CustomCardRepository {

    public void changeColumnOfCard(int order, Long cardId, Column column);

    public Optional<Card> fetchJoinCard(Long cardId);

    public Long getMaxOrderOfColumnByColumId(Long columId);
}
