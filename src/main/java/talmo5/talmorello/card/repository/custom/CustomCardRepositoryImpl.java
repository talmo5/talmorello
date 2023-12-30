package talmo5.talmorello.card.repository.custom;

import static talmo5.talmorello.card.entity.QCard.card;
import static talmo5.talmorello.column.entity.QColumn.column;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.column.entity.Column;

@RequiredArgsConstructor
public class CustomCardRepositoryImpl implements CustomCardRepository{

    private final JPAQueryFactory jpaQueryFactory;

    private final EntityManager em;

    @Override
    public void changeColumnOfCard(int order, Long cardId, Column column) {

        jpaQueryFactory
                .update(card)
                .set(card.orders, card.orders.add(1))
                .where(card.column.id.eq(column.getId()),
                        card.orders.goe(order))
                .execute();

        jpaQueryFactory
                .update(card)
                .set(card.orders, order)
                .set(card.column, column)
                .where(card.id.eq(cardId))
                .execute();

        em.clear();
    }

    @Override
    public Optional<Card> fetchJoinCard(Long cardId) {

        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(card)
                .join(card.column, column).fetchJoin()
                .where(card.id.eq(cardId))
                .fetchOne());
    }

    @Override
    public Long getMaxOrderOfColumnByColumId(Long columId) {

        return jpaQueryFactory
                .select(column.orders.count())
                .from(column)
                .where(column.id.eq(columId))
                .fetchOne();
    }
}
