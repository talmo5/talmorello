package talmo5.talmorello.global.exception.user;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class FailedLoginException extends BusinessException {

    public FailedLoginException(Throwable cause) {
        super(ErrorCode.FAILED_LOGIN_EXCEPTION, cause);
    }
}
