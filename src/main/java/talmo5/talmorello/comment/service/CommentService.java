package talmo5.talmorello.comment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.card.service.CardService;
import talmo5.talmorello.comment.dto.EditCommentDTO;
import talmo5.talmorello.comment.dto.RegisterCommentDTO;
import talmo5.talmorello.comment.entity.Comment;
import talmo5.talmorello.comment.respository.CommentRepository;
import talmo5.talmorello.global.exception.comment.CommentNotFoundException;
import talmo5.talmorello.user.entity.User;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CardService cardService;

//    private final UserService userService;

    private final CommentRepository commentRepository;

    public RegisterCommentDTO.Response registerComment(Long cardId, RegisterCommentDTO.Request requestDTO, Long userId) {

        // TODO 보드 사용자인지 검증 로직
//        Card card = cardService.findById(cardId);
//        User user = userService.findById(userId);
        Card card = Card.builder().build();
        User user = User.builder().build();

        Comment comment = requestDTO.createComment(requestDTO.commentContent(), card, user);

        Comment savedComment = commentRepository.save(comment);

        return RegisterCommentDTO.Response.of(savedComment);
    }

    @Transactional
    public void editComment(Long commentId, EditCommentDTO requestDTO) {

        // TODO 같은 서비스에 있는 것을 써도 되나?
        Comment comment = findById(commentId);

        comment.updateComment(requestDTO.commentContent());
    }

    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
    }
}
