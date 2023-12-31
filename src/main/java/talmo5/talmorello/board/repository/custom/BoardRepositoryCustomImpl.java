package talmo5.talmorello.board.repository.custom;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import talmo5.talmorello.board.dto.GetBoardDTO;
import talmo5.talmorello.board.entity.QBoard;
import talmo5.talmorello.card.entity.QCard;
import talmo5.talmorello.column.entity.QColumn;
import talmo5.talmorello.user.entity.QUser;

import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<GetBoardDTO.ColumnResponse> findByIdWithColumnListAndCardList(Long boardId) {
        QBoard board = QBoard.board;
        QColumn column = QColumn.column;
        QCard card = QCard.card;
        QUser user = QUser.user;

        return queryFactory.select(
                board.id, board.title, board.boardColor,
                        column.id, column.title, column.orders,
                        card.id, card.title, card.content, card.orders,
                        user.id, user.username
                )
                .from(board)
                .leftJoin(column)
                .on(board.id.eq(column.board.id))
                .leftJoin(card)
                .on(column.id.eq(card.column.id))
                .join(user)
                .on(user.id.eq(card.user.id))
                .where(board.id.eq(boardId))
                .orderBy(card.id.asc(), user.id.asc())
                .transform(
                        groupBy(column.id).list(
                                Projections.constructor(
                                        GetBoardDTO.ColumnResponse.class,
                                        column.id.as("columnId"),
                                        column.title.as("columnTitle"),
                                        column.orders.as("columnOrders"),
                                        GroupBy.list(
                                                Projections.constructor(
                                                        GetBoardDTO.CardResponse.class,
                                                        card.id.as("cardId"),
                                                        card.title.as("cardTitle"),
                                                        card.content.as("cardContent"),
                                                        card.orders.as("cardOrders"),
                                                        user.id.as("userId"),
                                                        user.username.as("username")
                                                )
                                        )
                                )
                        )
                );
    }
}
