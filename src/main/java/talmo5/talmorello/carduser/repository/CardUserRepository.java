package talmo5.talmorello.carduser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import talmo5.talmorello.carduser.entity.CardUser;
import talmo5.talmorello.carduser.pk.CardUserPK;
import talmo5.talmorello.carduser.repository.custom.CustomCardUserRepository;

public interface CardUserRepository extends JpaRepository<CardUser, CardUserPK>,
        CustomCardUserRepository {

}