package talmo5.talmorello.board.dto;

import lombok.Builder;
import talmo5.talmorello.board.constant.BoardColor;

import java.util.List;

public class GetBoardDTO {
    @Builder
    public record Response(
            Long boardId,
            String boardTitle,
            String boardContent,
            BoardColor boardColor,
            List<ColumnResponse> columnList
    ){}

    public record ColumnResponse(
            Long columnId,
            String columnTitle,
            int columnOrders,
            List<CardResponse> cardList
    ){}

    public record CardResponse(
            Long cardId,
            String cardTitle,
            String cardContent,
            int cardOrders,
            Long userId,
            String username
    ){}
}
