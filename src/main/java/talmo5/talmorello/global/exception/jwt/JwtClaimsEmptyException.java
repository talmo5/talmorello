package talmo5.talmorello.global.exception.jwt;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class JwtClaimsEmptyException extends BusinessException {
    public JwtClaimsEmptyException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }


}
