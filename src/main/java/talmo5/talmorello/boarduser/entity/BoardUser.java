package talmo5.talmorello.boarduser.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.boarduser.pk.BoardUserPK;
import talmo5.talmorello.user.entity.User;

@Getter
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardUser {

    @EmbeddedId
    private BoardUserPK boardUserPK;

    public static BoardUser buildBoardUser(Board board, User user){
        return BoardUser.builder()
                .boardUserPK(BoardUserPK.builder()
                        .user(user)
                        .board(board)
                        .build())
                .build();
    }

}
