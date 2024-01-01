package talmo5.talmorello.comment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.boarduser.validator.BoardUserValidator;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.card.service.CardService;
import talmo5.talmorello.comment.dto.EditCommentDTO;
import talmo5.talmorello.comment.dto.RegisterCommentDTO;
import talmo5.talmorello.comment.entity.Comment;
import talmo5.talmorello.comment.respository.CommentRepository;
import talmo5.talmorello.global.exception.board.BoardNotFoundException;
import talmo5.talmorello.global.exception.comment.CommentNotFoundException;
import talmo5.talmorello.global.exception.common.ErrorCode;
import talmo5.talmorello.global.exception.common.UnAuthorizedModifyException;
import talmo5.talmorello.user.entity.User;
import talmo5.talmorello.user.service.UserService;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CardService cardService;

    private final UserService userService;

    private final BoardUserValidator boardUserValidator;

    private final CommentRepository commentRepository;

    public RegisterCommentDTO.Response registerComment(Long cardId,
            RegisterCommentDTO.Request requestDTO, Long userId) {

        Card card = cardService.findById(cardId);
        Board board = cardService.getBoardByCardId(cardId);
        User user = userService.findById(userId);

        boardUserValidator.validateBoardUser(board, user);

        Comment comment = requestDTO.createComment(requestDTO.commentContent(), card, user);

        Comment savedComment = commentRepository.save(comment);

        return RegisterCommentDTO.Response.of(savedComment);
    }

    @Transactional
    public void editComment(Long commentId, EditCommentDTO requestDTO, Long userId) {

        Comment comment = findById(commentId);
        Board board = commentRepository.findBoardByCommentId(commentId)
                .orElseThrow(BoardNotFoundException::new);
        User user = userService.findById(userId);

        boardUserValidator.validateBoardUser(board, user);

        comment.updateComment(requestDTO.commentContent());
    }

    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
    }

    public void deleteComment(Long commentId, Long userId) {

        Comment comment = commentRepository.findByIdWithUser(commentId)
                .orElseThrow(CommentNotFoundException::new);

        Board board = commentRepository.findBoardByCommentId(commentId)
                .orElseThrow(BoardNotFoundException::new);
        User user = userService.findById(userId);

        boardUserValidator.validateBoardUser(board, user);

        validateDeleteAuthorization(comment.getUser().getId(), userId);

        commentRepository.deleteById(commentId);
    }

    public void validateDeleteAuthorization(Long userIdFromRequest, Long userIdFromDB) {
        if (!userIdFromDB.equals(userIdFromRequest)) {
            throw new UnAuthorizedModifyException(ErrorCode.UNAUTHORIZED_MODIFY_EXCEPTION);
        }
    }
}
