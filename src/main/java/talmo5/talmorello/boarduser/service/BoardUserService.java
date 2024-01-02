package talmo5.talmorello.boarduser.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.boarduser.entity.BoardUser;
import talmo5.talmorello.boarduser.repository.BoardUserRepository;
import talmo5.talmorello.global.exception.board.BoardUserNotFoundException;
import talmo5.talmorello.user.entity.User;

@Service
@RequiredArgsConstructor
public class BoardUserService {

    private final BoardUserRepository boardUserRepository;

    public boolean existBoardUserByUserId(Board board, User user) {

        return boardUserRepository.existBoardUserByUserId(board, user);
    }


    public void saveBoardUser(Board board, User inviteUser) {

        BoardUser boardUser = BoardUser.buildBoardUser(board, inviteUser);
        boardUserRepository.save(boardUser);
    }

    public void deleteBoardUser(Board board, User deleteUser) {

        BoardUser boardUser = boardUserRepository.findBoardUserByBoardAndUser(board, deleteUser)
                .orElseThrow(
                        BoardUserNotFoundException::new);
        boardUserRepository.delete(boardUser);
    }

    public List<User> getBoardUsers(Board board) {
        return boardUserRepository.findUsersByBoard(board);
    }
}
