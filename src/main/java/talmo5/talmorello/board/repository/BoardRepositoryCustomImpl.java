package talmo5.talmorello.board.repository;

import static com.querydsl.core.group.GroupBy.groupBy;
import static talmo5.talmorello.board.entity.QBoard.board;
import static talmo5.talmorello.card.entity.QCard.card;
import static talmo5.talmorello.column.entity.QColumn.column;
import static talmo5.talmorello.user.entity.QUser.user;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import talmo5.talmorello.board.dto.GetBoardDTO;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<GetBoardDTO.ColumnResponse> findByIdWithColumnListAndCardList(Long boardId) {

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
                .orderBy(column.id.asc(), card.id.asc(), user.id.asc())
                .transform(
                        groupBy(column.id).list(
                                Projections.constructor(
                                        GetBoardDTO.ColumnResponse.class,
                                        column.id,
                                        column.title,
                                        column.orders,
                                        GroupBy.list(
                                                Projections.constructor(
                                                        GetBoardDTO.CardResponse.class,
                                                        card.id,
                                                        card.title,
                                                        card.content,
                                                        card.orders,
                                                        user.id,
                                                        user.username
                                                )
                                        )
                                )
                        )
                );
    }
}