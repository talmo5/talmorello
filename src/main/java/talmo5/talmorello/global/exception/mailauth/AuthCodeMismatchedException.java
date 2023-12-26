package talmo5.talmorello.global.exception.mailauth;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class AuthCodeMismatchedException extends BusinessException {

    public AuthCodeMismatchedException() {
        super(ErrorCode.MISMATCHED_AUTH_CODE_EXCEPTION);
    }
}
