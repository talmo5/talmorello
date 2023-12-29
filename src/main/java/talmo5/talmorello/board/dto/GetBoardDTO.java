package talmo5.talmorello.board.dto;

import com.querydsl.core.types.Projections;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import talmo5.talmorello.board.constant.BoardColor;

public class GetBoardDTO {
    @Builder
    public record Response(
            String boardTitle,
            String boardContent,
            BoardColor boardColor,
            List<ColumnResponse> columnList
    ){}

    @Builder
    public record ColumnResponse(
            String columnTitle,
            List<CardResponse> cardList
    ){}

    @Builder
    public record CardResponse(
            String cardTitle,
            String cardContent,
            String username
    ){}

    public static Response buildResponse(Map<String,String> request){
        return Response.builder()
                .boardTitle(request.get("title"))
                .build();
    }
}
