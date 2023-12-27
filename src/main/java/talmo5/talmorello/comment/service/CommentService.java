package talmo5.talmorello.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.card.service.CardService;
import talmo5.talmorello.comment.dto.RegisterCommentDTO;
import talmo5.talmorello.comment.entity.Comment;
import talmo5.talmorello.comment.respository.CommentRepository;
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
}
