package talmo5.talmorello.global.exception.column;

import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorCode;

public class ColumnNotFoundException extends BusinessException {
  public ColumnNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }

}
