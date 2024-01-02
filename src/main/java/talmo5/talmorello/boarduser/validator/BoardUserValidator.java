package talmo5.talmorello.boarduser.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.boarduser.service.BoardUserService;
import talmo5.talmorello.global.exception.board.AlreadyUserOfBoardException;
import talmo5.talmorello.user.entity.User;

@Component
@RequiredArgsConstructor
public class BoardUserValidator {

    private final BoardUserService boardUserService;

    public void validateBoardUser(Board board, User user) {
        if (!boardUserService.existBoardUserByUserId(board, user)) {
            throw new AlreadyUserOfBoardException();
        }
    }

}
