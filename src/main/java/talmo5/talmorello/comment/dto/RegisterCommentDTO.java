package talmo5.talmorello.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.comment.entity.Comment;
import talmo5.talmorello.user.entity.User;

public class RegisterCommentDTO {

    public record Request(@NotBlank String commentContent) {

        public Comment createComment(String commentContent, Card card, User user) {
            return Comment.builder()
                    .content(commentContent)
                    .card(card)
                    .user(user)
                    .build();
        }

    }

    @Builder
    public record Response(
            Long commentId,
            String commentContent,
            String username) {

        public static Response of(Comment comment) {
            return Response.builder()
                    .commentId(comment.getId())
                    .commentContent(comment.getContent())
                    .username(comment.getUser().getUsername())
                    .build();
        }
    }
}
