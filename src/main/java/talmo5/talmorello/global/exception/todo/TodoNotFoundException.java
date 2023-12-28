package talmo5.talmorello.global.exception.todo;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class TodoNotFoundException extends BusinessException {

    public TodoNotFoundException() {
        super(ErrorCode.NOT_FOUND_TODO_EXCEPTION);
    }
}
