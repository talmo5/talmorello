package talmo5.talmorello.board.dto;

import org.hibernate.validator.constraints.Length;
import talmo5.talmorello.board.constant.BoardColor;

public class ModifyBoardDTO {

    public record Request(
            @Length(max = 50)
            String title,
            @Length(max = 500)
            String content,
            BoardColor boardColor) {}
}
