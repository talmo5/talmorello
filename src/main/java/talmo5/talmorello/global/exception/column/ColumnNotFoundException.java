package talmo5.talmorello.global.exception.column;

import static talmo5.talmorello.global.exception.common.ErrorCode.NOT_FOUND_COLUMN_EXCEPTION;

import talmo5.talmorello.global.exception.common.BusinessException;

public class ColumnNotFoundException extends BusinessException {
  public ColumnNotFoundException() {
    super(NOT_FOUND_COLUMN_EXCEPTION);
  }

}
