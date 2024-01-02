package talmo5.talmorello.boarduser.repository.custom;

import talmo5.talmorello.user.entity.User;

import java.util.List;

public interface BoardUserRepositoryCustom {
    public boolean existBoardUserByUserId(Long boardId, Long userId);
    public List<User> findUserByBoardId(Long boardId);
}
