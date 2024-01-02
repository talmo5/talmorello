package talmo5.talmorello.global.exception.jwt;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class JwtNotFoundException extends BusinessException {

    public JwtNotFoundException() {
        super(ErrorCode.NO_JWT_EXCEPTION);
    }
}

