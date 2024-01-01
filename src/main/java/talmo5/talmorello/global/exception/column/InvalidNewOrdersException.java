package talmo5.talmorello.global.exception.column;

import static talmo5.talmorello.global.exception.common.ErrorCode.INVALID_NEW_ORDERS_EXCEPTION;

import talmo5.talmorello.global.exception.common.BusinessException;

public class InvalidNewOrdersException extends BusinessException {

  public InvalidNewOrdersException() {
    super(INVALID_NEW_ORDERS_EXCEPTION);
  }
}
