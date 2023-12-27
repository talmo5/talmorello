package talmo5.talmorello.card.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Builder;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.user.entity.User;

public class CreateCardDTO {

    public record Request(
            @NotBlank @Size(max = 50) String cardTitle
    ) {

        public Card toEntity(String cardTitle, User user, int orders) {
            return Card.builder()
                    .title(cardTitle)
                    .user(user)
                    .orders(orders)
                    .build();
        }
    }

    //추후 댓글들과 체크리스트도 보내줘야함
    @Builder
    public record Response(Long cardId, String cardTitle, String cardDescription, String username,
                           LocalDateTime startDate, LocalDateTime dueDate, LocalDateTime createdAt
                           ) {

        public static Response of(Card card) {

            return Response.builder()
                    .cardId(card.getId())
                    .cardTitle(card.getTitle())
                    .cardDescription(card.getContent())
                    .username(card.getUser().getUsername())
                    .startDate(card.getStartDate())
                    .dueDate(card.getDueDate())
                    .createdAt(card.getCreatedAt())
                    .build();
        }
    }
}
