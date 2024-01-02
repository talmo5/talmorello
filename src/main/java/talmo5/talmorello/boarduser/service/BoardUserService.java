package talmo5.talmorello.boarduser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talmo5.talmorello.boarduser.repository.BoardUserRepository;
import talmo5.talmorello.user.entity.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardUserService {

    private final BoardUserRepository boardUserRepository;

    public boolean existBoardUserByUserId(Long boardId, Long userId) {

        return boardUserRepository.existBoardUserByUserId(boardId, userId);
    }

    public List<User> findUserByBoardId(Long boardId) {
        return boardUserRepository.findUserByBoardId(boardId);
    }

}