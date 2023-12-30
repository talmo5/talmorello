package talmo5.talmorello.board.dto;

import java.util.List;
import talmo5.talmorello.board.constant.BoardColor;

public class GetBoardDTO {
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
            int orders,
            List<CardResponse> cardList
    ){}

    public record CardResponse(
            Long cardId,
            String cardTitle,
            String cardContent,
            String username
    ){}
}
