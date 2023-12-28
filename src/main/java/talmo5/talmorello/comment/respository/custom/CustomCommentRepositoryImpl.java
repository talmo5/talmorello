package talmo5.talmorello.comment.respository.custom;

import static talmo5.talmorello.comment.entity.QComment.comment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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
}
