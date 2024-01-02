package talmo5.talmorello.global.exception.user;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class UserEmailAlreadyExistException extends BusinessException {

    public UserEmailAlreadyExistException() {
        super(ErrorCode.ALREADY_EXIST_EMAIL_EXCEPTION);
    }
}