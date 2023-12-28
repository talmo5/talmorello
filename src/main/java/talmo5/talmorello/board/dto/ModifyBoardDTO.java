package talmo5.talmorello.board.dto;

import lombok.Builder;
import org.hibernate.validator.constraints.Length;
import talmo5.talmorello.board.constant.BoardColor;
import talmo5.talmorello.board.entity.Board;

public class ModifyBoardDTO {

    public static String[] fill(Request requestDto, Board board) {
        String title = requestDto.title(), content = requestDto.content();
        String[] tc = {title, content};
        if(tc[0].isEmpty()) tc[0] = board.getTitle();
        if(tc[1].isEmpty()) tc[1] = board.getContent();
        return tc;
    }

    @Builder
    public record Request(
            @Length(max = 50)
            String title,
            @Length(max = 500)
            String content,
            BoardColor boardColor) {}
}
