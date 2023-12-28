package talmo5.talmorello.card.repository.custom;

import static talmo5.talmorello.card.entity.QCard.card;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import talmo5.talmorello.column.entity.Column;

@RequiredArgsConstructor
public class CustomCardRepositoryImpl implements CustomCardRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void addOneToCardOrders(int order, Column column) {

        jpaQueryFactory.
                update(card)
                .set(card.orders, card.orders.add(1))
                .where(card.column.eq(column),
                        card.orders.gt(order))
                .execute();
    }
}
