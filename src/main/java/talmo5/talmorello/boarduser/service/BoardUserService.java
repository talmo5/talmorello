package talmo5.talmorello.boarduser.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.boarduser.repository.BoardUserRepository;
import talmo5.talmorello.user.entity.User;

@Service
@RequiredArgsConstructor
public class BoardUserService {

    private final BoardUserRepository boardUserRepository;

    public boolean existBoardUserByUserId(Board board, User user) {

        return boardUserRepository.existBoardUserByUserId(board, user);
    }

    public List<User> findUserByBoardId(Long boardId) {
        return boardUserRepository.findUserByBoardId(boardId);
    }

}