package talmo5.talmorello.global.exception.board;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class BoardNotFoundException extends BusinessException {

    public BoardNotFoundException() {
        super(ErrorCode.NOT_FOUND_BOARD_EXCEPTION);
    }
}