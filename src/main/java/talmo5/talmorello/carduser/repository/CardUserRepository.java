package talmo5.talmorello.carduser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.carduser.entity.CardUser;
import talmo5.talmorello.carduser.pk.CardUserPK;
import talmo5.talmorello.carduser.repository.custom.CustomCardUserRepository;
import talmo5.talmorello.user.entity.User;

public interface CardUserRepository extends JpaRepository<CardUser, CardUserPK>,
        CustomCardUserRepository {

    public void deleteCardUserByCardUserPK_CardAndCardUserPK_User(Card card, User user);

}