package talmo5.talmorello.global.exception.common;

public class UnAuthorizedModifyException extends BusinessException{

    public UnAuthorizedModifyException(ErrorCode errorCode) {
        super(errorCode);
    }
}
