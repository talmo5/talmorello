package talmo5.talmorello.card.repository.custom;

import static talmo5.talmorello.board.entity.QBoard.board;
import static talmo5.talmorello.card.entity.QCard.card;
import static talmo5.talmorello.column.entity.QColumn.column;
import static talmo5.talmorello.comment.entity.QComment.comment;
import static talmo5.talmorello.todo.entity.QTodo.todo;
import static talmo5.talmorello.user.entity.QUser.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.card.entity.QCard;
import talmo5.talmorello.column.entity.Column;
import talmo5.talmorello.column.entity.QColumn;
import talmo5.talmorello.user.entity.QUser;

@RequiredArgsConstructor
public class CustomCardRepositoryImpl implements CustomCardRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final EntityManager em;

    @Override
    public void changeColumnOfCard(int order, Card cardToMove, Column newColumn, Column oldColumn) {

        jpaQueryFactory
                .update(card)
                .set(card.orders, card.orders.subtract(1))
                .where(card.column.id.eq(oldColumn.getId()),
                        card.orders.gt(cardToMove.getOrders()))
                .execute();

        jpaQueryFactory
                .update(card)
                .set(card.orders, card.orders.add(1))
                .where(card.column.id.eq(newColumn.getId()),
                        card.orders.goe(order))
                .execute();

        jpaQueryFactory
                .update(card)
                .set(card.orders, order)
                .set(card.column, newColumn)
                .where(card.eq(cardToMove))
                .execute();

        em.flush();
        em.clear();
    }

    @Override
    public Optional<Card> getCardWithColumn(Long cardId) {

        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(card)
                .join(card.column, column).fetchJoin()
                .where(card.id.eq(cardId))
                .fetchOne());
    }

    @Override
    public Long getMaxOrderOfCardByColumnId(Long columnId) {

        return jpaQueryFactory
                .select(card.orders.count())
                .from(card)
                .where(card.column.id.eq(columnId))
                .fetchOne();
    }

    @Override
    public Optional<Board> getBoardByCardId(Long cardId) {

        return Optional.ofNullable(jpaQueryFactory
                .select(board)
                .from(card)
                .join(column).on(card.column.id.eq(column.id))
                .join(board).on(column.board.id.eq(board.id))
                .where(card.id.eq(cardId))
                .fetchOne());
    }

    @Override
    public void subtractCardOrdersToDeleteCard(Card card, Column column) {

        jpaQueryFactory
                .update(QCard.card)
                .set(QCard.card.orders, QCard.card.orders.subtract(1))
                .where(QColumn.column.eq(column),
                        QCard.card.orders.gt(card.getOrders()))
                .execute();

        em.flush();
        em.clear();
    }

    @Override
    public void changeOrders(Long cardId, Long columnId, int newOrders, int oldOrders) {

        if (newOrders > oldOrders) {
            jpaQueryFactory
                    .update(card)
                    .set(card.orders, card.orders.subtract(1))
                    .where(card.orders.gt(oldOrders),
                            card.orders.loe(newOrders),
                            card.column.id.eq(columnId))
                    .execute();
        } else {
            jpaQueryFactory
                    .update(card)
                    .set(card.orders, card.orders.add(1))
                    .where(card.orders.lt(oldOrders),
                            card.orders.goe(newOrders),
                            card.column.id.eq(columnId))
                    .execute();
        }

        jpaQueryFactory.update(card)
                .set(card.orders, newOrders)
                .where(card.id.eq(cardId))
                .execute();

        em.flush();
        em.clear();
    }

    @Override
    public Optional<Card> getCardWithUserAndCommentListAndTodoList(Long cardId) {

        QUser commentUser = new QUser("commentUser");
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(card)
                .leftJoin(card.user, user).fetchJoin()
                .leftJoin(card.commentList, comment).fetchJoin()
                .leftJoin(comment.user, commentUser).fetchJoin()
                .leftJoin(card.todoList, todo).fetchJoin()
                .where(card.id.eq(cardId))
                .fetchOne());
    }
}
