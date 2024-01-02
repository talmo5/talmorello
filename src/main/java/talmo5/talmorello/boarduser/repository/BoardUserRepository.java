package talmo5.talmorello.boarduser.repository;

import static talmo5.talmorello.boarduser.entity.QBoardUser.boardUser;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.boarduser.entity.BoardUser;
import talmo5.talmorello.user.entity.User;

@Repository
@RequiredArgsConstructor
public class BoardUserRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final EntityManager em;

    public boolean existBoardUserByUserId(Board board, User user) {
        return jpaQueryFactory
                .from(boardUser)
                .where(boardUser.boardUserPK.board.eq(board)
                        .and(boardUser.boardUserPK.user.eq(user)))
                .fetchFirst() != null;
    }

    public Optional<BoardUser> findBoardUserByBoardAndUser(Board board, User user) {
        return Optional.ofNullable(jpaQueryFactory
                .select(boardUser)
                .from(boardUser)
                .where(boardUser.boardUserPK.board.eq(board)
                        .and(boardUser.boardUserPK.user.eq(user)))
                .fetchOne());
    }

    public void save(BoardUser boardUser) {
        em.persist(boardUser);
        em.flush();
    }

    public void delete(BoardUser boardUser) {
        em.remove(boardUser);
        em.flush();
    }
}
