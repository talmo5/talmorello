package talmo5.talmorello.carduser.repository.custom;

import java.util.List;
import talmo5.talmorello.user.entity.User;

public interface CustomCardUserRepository {

    List<User> findUserByCardId(Long cardId);
}
