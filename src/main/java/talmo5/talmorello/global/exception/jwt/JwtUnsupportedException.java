package talmo5.talmorello.global.exception.jwt;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class JwtUnsupportedException extends BusinessException {
    public JwtUnsupportedException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

}
