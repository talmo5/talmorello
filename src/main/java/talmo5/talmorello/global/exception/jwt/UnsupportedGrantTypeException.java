package talmo5.talmorello.global.exception.jwt;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class UnsupportedGrantTypeException extends BusinessException {

    public UnsupportedGrantTypeException() {
        super(ErrorCode.NOT_SUPPORTED_GRANT_TYPE_EXCEPTION);
    }
}
