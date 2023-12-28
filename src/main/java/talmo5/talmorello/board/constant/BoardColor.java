package talmo5.talmorello.board.constant;

import java.util.Arrays;

public enum BoardColor {
    PINK("Pink"), PURPLE("Purple"), YELLOW("Yellow"), ORANGE("Orange"), WHITE("White");

    private final String boardColor;

    BoardColor(String boardColor) { this.boardColor = boardColor; }

    public static BoardColor valueOfLabel(String boardColor) {
        return Arrays.stream(values()).filter(value -> value.boardColor.equals(boardColor)).findAny().orElseThrow((()
                -> new NullPointerException("해당 색상을 찾을 수 없습니다.")));
    }
}
