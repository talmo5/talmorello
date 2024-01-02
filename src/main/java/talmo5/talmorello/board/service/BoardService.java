package talmo5.talmorello.board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import talmo5.talmorello.board.dto.GetBoardDTO;
import talmo5.talmorello.board.dto.ModifyBoardDTO;
import talmo5.talmorello.board.dto.PostBoardDTO;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.board.repository.BoardRepository;
import talmo5.talmorello.boarduser.service.BoardUserService;
import talmo5.talmorello.boarduser.validator.BoardUserValidator;
import talmo5.talmorello.global.exception.board.BoardNotFoundException;
import talmo5.talmorello.global.exception.board.UnauthorizationInviteException;
import talmo5.talmorello.user.entity.User;
import talmo5.talmorello.user.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService{

    private final UserService userService;

    private final BoardUserValidator boardUserValidator;

    private final BoardUserService boardUserService;

    private final BoardRepository boardRepository;

    @Transactional
    public void postBoard(PostBoardDTO.Request requestDto, Long userId) {

        Board savedBoard = buildBoard(requestDto, userId);
        User user = userService.findById(userId);

        boardUserService.saveBoardUser(savedBoard, user);
    }

    private Board buildBoard(PostBoardDTO.Request requestDto, Long userId){

        User user = userService.findById(userId);
        Board board = PostBoardDTO.BoardBuild(requestDto, user);

        return boardRepository.save(board);
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

    public Board findById(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
    }

    @Transactional
    public GetBoardDTO.Response getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
        return GetBoardDTO.Response.builder()
                .boardId(board.getId())
                .boardTitle(board.getTitle())
                .boardContent(board.getContent())
                .boardColor(board.getBoardColor())
                .columnList(boardRepository.findByIdWithColumnListAndCardList(boardId))
                .build();
    }

    @Transactional
    public void inviteBoardUser(Long boardId, Long inviteUserId, Long userId) {

        Board board = findById(boardId);

        User user = userService.findById(userId);
        boardUserValidator.validateBoardUser(board, user);

        User inviteUser = userService.findById(inviteUserId);
        boardUserValidator.validateDuplicateBoardUser(board, inviteUser);

        boardUserService.saveBoardUser(board, inviteUser);
    }

    @Transactional
    public void deleteBoardUser(Long boardId, Long deleteUserId, Long userId) {

        Board board = findById(boardId);

        if (!isBoardOwner(board.getUser().getId(), userId)) {
            throw new UnauthorizationInviteException();
        }

        User deleteUser = userService.findById(deleteUserId);
        boardUserService.deleteBoardUser(board, deleteUser);
    }

    public boolean isBoardOwner(Long userIdFromBoard, Long loginUserId) {
        return userIdFromBoard.equals(loginUserId);
    }
}

