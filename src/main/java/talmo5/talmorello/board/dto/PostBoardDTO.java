package talmo5.talmorello.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;
import talmo5.talmorello.board.constant.BoardColor;

public class PostBoardDTO {

    public record Request(
            @NotBlank @Length(max = 50)
            String title,
            @NotBlank @Length(max = 500)
            String content,
            @NotBlank
            BoardColor boardColor) {

    }

    @Builder
    public record Response(String title,
                           String content,
                           BoardColor board_color) {

    }


}
