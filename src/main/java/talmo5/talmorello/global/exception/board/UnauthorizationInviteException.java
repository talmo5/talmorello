package talmo5.talmorello.global.exception.board;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class UnauthorizationInviteException extends BusinessException {

    public UnauthorizationInviteException() {
        super(ErrorCode.NO_AUTHORIZATION_REMOVE_EXCEPTION);
    }
}
