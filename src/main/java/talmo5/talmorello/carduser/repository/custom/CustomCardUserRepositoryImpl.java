package talmo5.talmorello.carduser.repository.custom;

import static talmo5.talmorello.carduser.entity.QCardUser.cardUser;
import static talmo5.talmorello.user.entity.QUser.user;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import talmo5.talmorello.carduser.entity.CardUser;
import talmo5.talmorello.user.entity.User;

@RequiredArgsConstructor
public class CustomCardUserRepositoryImpl implements CustomCardUserRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final EntityManager em;

    @Override
    public List<User> findUsersByCardId(Long cardId) {

        return jpaQueryFactory
                .selectFrom(user)
                .where(user.id.in(
                                JPAExpressions
                                        .select(cardUser.cardUserPK.user.id)
                                        .from(cardUser)
                                        .where(cardUser.cardUserPK.card.id.eq(cardId))
                        )
                )
                .fetch();
    }

    @Override
    public Optional<CardUser> findCardUserByCardIdAndUserId(Long cardId, Long userId) {

        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(cardUser)
                .where(cardUser.cardUserPK.card.id.eq(cardId)
                        .and(cardUser.cardUserPK.user.id.eq(userId))
                )
                .fetchOne());
    }

    @Override
    public void deleteAllCardUserByCardId(Long cardId) {

        long result = jpaQueryFactory
                .delete(cardUser)
                .where(cardUser.cardUserPK.card.id.eq(cardId))
                .execute();

        em.flush();
        em.clear();
    }
}
