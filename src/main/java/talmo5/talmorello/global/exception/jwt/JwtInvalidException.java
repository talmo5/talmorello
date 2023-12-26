package talmo5.talmorello.global.exception.jwt;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class JwtInvalidException extends BusinessException {

    public JwtInvalidException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
