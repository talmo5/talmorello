package talmo5.talmorello.global.exception.board;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class BoardUserNotFoundException extends BusinessException {

    public BoardUserNotFoundException() {
        super(ErrorCode.NOT_FOUND_BOARD_USER_EXCEPTION);
    }
}
