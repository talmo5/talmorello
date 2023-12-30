package talmo5.talmorello.column.repository.custom;

import java.util.Optional;
import talmo5.talmorello.column.entity.Column;

public interface CustomColumnRepository {

  public void addOneToColumnOrders(Long boardId, Long columnId, int newOrders, int oldOrders);
  public void subtractOneToColumnOrders(Long boardId, Long columnId, int newOrders, int oldOrders);
  public Optional<Column> getColumnWithBoard(Long columnId);
  public Long orderCount();

}
