package talmo5.talmorello.column.repository.custom;

import talmo5.talmorello.board.entity.Board;

public interface CustomColumnRepository {

  public void addOneToColumnOrders(Board board, int newOrders, int oldOrders);
  public void subtractOneToColumnOrders(Board board, int newOrders, int oldOrders);

}
