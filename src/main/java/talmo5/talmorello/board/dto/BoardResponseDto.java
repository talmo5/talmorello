package talmo5.talmorello.board.dto;

import lombok.Getter;
import talmo5.talmorello.board.constant.BoardColor;
import talmo5.talmorello.board.entity.Board;

@Getter
public class BoardResponseDto {
    private String title;
    private String content;
    private BoardColor board_color;
    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.board_color = board.getBoardColor();
    }
}
