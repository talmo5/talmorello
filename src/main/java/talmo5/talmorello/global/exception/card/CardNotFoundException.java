package talmo5.talmorello.global.exception.card;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class CardNotFoundException extends BusinessException {

    public CardNotFoundException() {
        super(ErrorCode.NOT_FOUND_CARD_EXCEPTION);
    }
}
