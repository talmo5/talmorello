package talmo5.talmorello.carduser.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.carduser.pk.CardUserPK;
import talmo5.talmorello.user.entity.User;

@Getter
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardUser {

    @EmbeddedId
    private CardUserPK cardUserPK;

    public static CardUser makeCardUser(Card card, User user) {

        CardUserPK cardUserPK = CardUserPK.builder()
                .user(user)
                .card(card)
                .build();

        return CardUser.builder()
                .cardUserPK(cardUserPK)
                .build();
    }
}
