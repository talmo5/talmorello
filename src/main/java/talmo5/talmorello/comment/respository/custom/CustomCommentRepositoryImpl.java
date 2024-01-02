package talmo5.talmorello.comment.respository.custom;

import static talmo5.talmorello.board.entity.QBoard.board;
import static talmo5.talmorello.card.entity.QCard.card;
import static talmo5.talmorello.column.entity.QColumn.column;
import static talmo5.talmorello.comment.entity.QComment.comment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.comment.entity.Comment;

@RequiredArgsConstructor
public class CustomCommentRepositoryImpl implements CustomCommentRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Comment> findByIdWithUser(Long commentId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(comment)
                .from(comment)
                .join(comment.user).fetchJoin()
                .where(comment.id.eq(commentId))
                .fetchOne());
    }

    @Override
    public Optional<Board> findBoardByCommentId(Long commentId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(board)
                .from(comment)
                .join(card).on(comment.card.id.eq(card.id))
                .join(column).on(card.column.id.eq(column.id))
                .join(board).on(column.board.id.eq(board.id))
                .where(comment.id.eq(commentId))
                .fetchOne()
        );
    }
}
