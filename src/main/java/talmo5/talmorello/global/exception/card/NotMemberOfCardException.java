package talmo5.talmorello.global.exception.card;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class NotMemberOfCardException extends BusinessException {

    public NotMemberOfCardException() {
        super(ErrorCode.NOT_MEMBER_OF_CARD_EXCEPTION);
    }

}
