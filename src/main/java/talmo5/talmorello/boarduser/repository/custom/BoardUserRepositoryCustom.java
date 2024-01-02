package talmo5.talmorello.boarduser.repository.custom;

import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.user.entity.User;

import java.util.List;

public interface BoardUserRepositoryCustom {
    boolean existBoardUserByUserId(Board board, User user);
    List<User> findUserByBoardId(Long boardId);
}
