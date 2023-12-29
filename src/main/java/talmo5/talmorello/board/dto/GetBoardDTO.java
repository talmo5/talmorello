package talmo5.talmorello.board.dto;

import lombok.Builder;
import talmo5.talmorello.board.constant.BoardColor;

public class GetBoardDTO {

    @Builder
    public record Response(
            String title,
            String content,
            BoardColor boardColor
            // 컬럼과 카드를 가져올 dto 필요.
    ){}

    public static Response buildResponse(String title, String content, BoardColor boardColor/*...*/){
        return Response.builder()
                .title(title)
                .content(content)
                .boardColor(boardColor)
                //...
                .build();
    }
}
