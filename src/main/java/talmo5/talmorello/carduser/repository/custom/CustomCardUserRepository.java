package talmo5.talmorello.carduser.repository.custom;

import java.util.List;
import java.util.Optional;
import talmo5.talmorello.carduser.entity.CardUser;
import talmo5.talmorello.user.entity.User;

public interface CustomCardUserRepository {

    List<User> findUsersByCardId(Long cardId);

    Optional<CardUser> findCardUserByCardIdAndUserId(Long cardId, Long userId);
}
