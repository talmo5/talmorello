package talmo5.talmorello.boarduser.repository.custom;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.user.entity.User;

import java.util.List;

import static talmo5.talmorello.boarduser.entity.QBoardUser.boardUser;
import static talmo5.talmorello.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class BoardUserRepositoryCustomImpl implements BoardUserRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public boolean existBoardUserByUserId(Board board, User user) {
        return jpaQueryFactory
                .from(boardUser)
                .where(boardUser.boardUserPK.board.eq(board)
                        .and(boardUser.boardUserPK.user.eq(user)))
                .fetchFirst() != null;
    }

    @Override
    public List<User> findUserByBoardId(Long boardId) {
        return jpaQueryFactory
                .select(user)
                .from(user)
                .where(user.id.in(
                        JPAExpressions
                                .select(boardUser.boardUserPK.user.id)
                                .from(boardUser)
                                .where(boardUser.boardUserPK.board.id.eq(boardId))
                )).fetch();
    }
}