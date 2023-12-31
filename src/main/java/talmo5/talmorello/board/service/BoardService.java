package talmo5.talmorello.board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talmo5.talmorello.board.dto.GetBoardDTO;
import talmo5.talmorello.board.dto.ModifyBoardDTO;
import talmo5.talmorello.board.dto.PostBoardDTO;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.board.repository.BoardRepository;
import talmo5.talmorello.boarduser.entity.BoardUser;
import talmo5.talmorello.boarduser.repository.BoardUserRepository;
import talmo5.talmorello.global.exception.board.AlreadyUserOfBoardException;
import talmo5.talmorello.global.exception.board.BoardNotFoundException;
import talmo5.talmorello.global.exception.user.UserNotFoundException;
import talmo5.talmorello.user.entity.User;
import talmo5.talmorello.user.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BoardService{
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardUserRepository boardUserRepository;

    public void postBoard(PostBoardDTO.Request requestDto) {
        buildBoard(requestDto);
    }

    private void buildBoard(PostBoardDTO.Request requestDto){
        Board board = PostBoardDTO.BoardBuild(requestDto);
        boardRepository.save(board);
    }

    @Transactional
    public void patchBoard(ModifyBoardDTO.Request requestDto, Long boardId) {
        Board board = findById(boardId);
        board.update(requestDto.title(), requestDto.content(), requestDto.boardColor());
    }

    public void deleteBoard(Long boardId) {
        Board board = findById(boardId);
        boardRepository.delete(board);
    }

    public GetBoardDTO.Response getBoard(Long boardId) {
        Board board = findById(boardId);
        return GetBoardDTO.Response.builder()
                .boardId(board.getId())
                .boardTitle(board.getTitle())
                .boardContent(board.getContent())
                .boardColor(board.getBoardColor())
                .columnList(boardRepository.findByIdWithColumnListAndCardList(boardId))
                .build();
    }

    public void inviteUser(Long boardId, Long inviteUserId){
        Board board = findById(boardId);
        User inviteUser = userRepository.findById(inviteUserId).orElseThrow(UserNotFoundException::new);

        findUserInboard(inviteUser, boardId);

        BoardUser boardUser = BoardUser.buildBoardUser(board, inviteUser);
        boardUserRepository.save(boardUser);
    }

    private void findUserInboard(User user, Long boardId){
        List<User> users = boardUserRepository.findUserByBoardId(boardId);
        for(User u : users){
            if(Objects.equals(user.getId(), u.getId())){
                throw new AlreadyUserOfBoardException();
            }
        }
    }

    private Board findById(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
    }
}

