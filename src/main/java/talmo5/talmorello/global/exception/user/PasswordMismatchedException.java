package talmo5.talmorello.global.exception.user;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class PasswordMismatchedException extends BusinessException {

    public PasswordMismatchedException() {
        super(ErrorCode.MISMATCHED_PASSWORD_EXCEPTION);
    }
}
