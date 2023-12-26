package talmo5.talmorello.global.exception.comment;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class CommentNotFoundException extends BusinessException {

    public CommentNotFoundException() {
        super(ErrorCode.NOT_FOUND_COMMENT_EXCEPTION);
    }
}
