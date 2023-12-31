package talmo5.talmorello.card.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Builder;
import talmo5.talmorello.card.constant.Priority;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.column.entity.Column;
import talmo5.talmorello.user.entity.User;

public class CreateCardDTO {

    public record Request(
            @NotBlank @Size(max = 50) String cardTitle
    ) {

        public Card toEntity(String cardTitle, User user, int orders, Column column) {
            return Card.builder()
                    .title(cardTitle)
                    .user(user)
                    .priority(Priority.COMMON)
                    .orders(orders)
                    .column(column)
                    .build();
        }
    }

    @Builder
    public record Response(Long cardId, String cardTitle, String username, LocalDateTime createdAt) {

        public static Response of(Card card) {

            return Response.builder()
                    .cardId(card.getId())
                    .cardTitle(card.getTitle())
                    .username(card.getUser().getUsername())
                    .createdAt(card.getCreatedAt())
                    .build();
        }
    }
}
