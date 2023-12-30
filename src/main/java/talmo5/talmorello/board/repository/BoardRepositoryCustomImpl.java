package talmo5.talmorello.board.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import talmo5.talmorello.board.dto.GetBoardDTO;
import talmo5.talmorello.board.entity.QBoard;
import talmo5.talmorello.card.entity.QCard;
import talmo5.talmorello.column.entity.QColumn;
import talmo5.talmorello.user.entity.QUser;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<GetBoardDTO.Response> findByIdWithCatalogListAndCardList(Long boardId) {
        QBoard board = QBoard.board;
        QColumn column = QColumn.column;
        QCard card = QCard.card;
        QUser user = QUser.user;
        /*
          select
              b.id, b.title, b.content, b.board_color, c.id, c.title, c.orders, cd.id, cd.title, cd.content, cd.orders, u.id, u.username"
          from
              board b
          left join
              columns c
          on
              b.id = c.board_id
          left join
              talmorello.card cd
          on
              c.id = cd.column_id
          join
              talmorello.user u
          on
              cd.user_id = u.id
          where
              b.id = :boardId
         */
        // InvalidDataAccessApiUsageException: org.hibernate.query.sqm.InterpretationException
        // this may indicate a semantic (user query) problem or a bug in the parser
        // InterpretationException: Error interpreting query
        return queryFactory.select(
                        Projections.constructor(
                                GetBoardDTO.Response.class,
                                board.id,
                                board.title,
                                board.content,
                                board.boardColor,
                                Projections.list(
                                        Projections.constructor(
                                                GetBoardDTO.ColumnResponse.class,
                                                column.id,
                                                column.title,
                                                column.orders,
                                                Projections.list(
                                                        Projections.constructor(
                                                                GetBoardDTO.CardResponse.class,
                                                                card.id,
                                                                card.title,
                                                                card.content,
                                                                user.username
                                                        )
                                                )
                                        )
                                )
                        )
                )
                .from(board)
                .leftJoin(column.board, board).on(column.board.id.eq(board.id))
                .leftJoin(card.column, column).on(card.column.id.eq(column.id))
                .join(card.user, user).on(card.user.id.eq(user.id))
                .where(board.id.eq(boardId))
                .fetch();
    }


}
