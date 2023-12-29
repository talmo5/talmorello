package talmo5.talmorello.card.dto;

import java.util.List;
import talmo5.talmorello.user.entity.User;

public record CardMemberDTO(List<User> memberList) {

    public static CardMemberDTO of(List<User> memberList) {

        return new CardMemberDTO(memberList);
    }
}
