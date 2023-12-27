package talmo5.talmorello.carduser.repository.custom;

import static talmo5.talmorello.carduser.entity.QCardUser.cardUser;
import static talmo5.talmorello.user.entity.QUser.user;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import talmo5.talmorello.user.entity.User;

@RequiredArgsConstructor
public class CustomCardUserRepositoryImpl implements CustomCardUserRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<User> findUserByCardId(Long cardId){

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
}
