package talmo5.talmorello.carduser.repository.custom;

import static talmo5.talmorello.carduser.entity.QCardUser.cardUser;
import static talmo5.talmorello.user.entity.QUser.user;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import talmo5.talmorello.carduser.entity.CardUser;
import talmo5.talmorello.user.entity.User;

@RequiredArgsConstructor
public class CustomCardUserRepositoryImpl implements CustomCardUserRepository {

    private final JPAQueryFactory jpaQueryFactory;

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


}
