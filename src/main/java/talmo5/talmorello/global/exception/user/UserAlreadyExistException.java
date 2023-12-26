package talmo5.talmorello.global.exception.user;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class UserAlreadyExistException extends BusinessException {

    public UserAlreadyExistException() {
        super(ErrorCode.ALREADY_EXIST_USER_NAME_EXCEPTION);
    }
}
