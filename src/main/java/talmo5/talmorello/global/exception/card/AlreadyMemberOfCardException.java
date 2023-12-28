package talmo5.talmorello.global.exception.card;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class AlreadyMemberOfCardException extends BusinessException {

    public AlreadyMemberOfCardException() {
        super(ErrorCode.ALREADY_MEMBER_OF_CARD_EXCEPTION);
    }

}
