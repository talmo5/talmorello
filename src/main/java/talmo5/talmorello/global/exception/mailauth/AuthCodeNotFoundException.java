package talmo5.talmorello.global.exception.mailauth;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class AuthCodeNotFoundException extends BusinessException {

    public AuthCodeNotFoundException() {
        super(ErrorCode.NOT_FOUND_AUTH_CODE_EXCEPTION);
    }
}
