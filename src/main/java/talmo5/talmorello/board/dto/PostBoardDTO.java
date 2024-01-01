package talmo5.talmorello.board.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import talmo5.talmorello.board.constant.BoardColor;
import talmo5.talmorello.board.entity.Board;

public class PostBoardDTO {

    public record Request(
            @NotBlank @Length(max = 50)
            String title,
            @NotBlank @Length(max = 500)
            String content,
            BoardColor boardColor) {}

    public static Board BoardBuild(PostBoardDTO.Request request){
        return Board.builder()
                .title(request.title())
                .content(request.content())
                .boardColor(request.boardColor())
                .build();
    }
}
