package talmo5.talmorello.global.exception.board;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class AlreadyUserOfBoardException extends BusinessException {

    public AlreadyUserOfBoardException() {
        super(ErrorCode.ALREADY_USER_OF_BOARD_EXCEPTION);
    }
}

