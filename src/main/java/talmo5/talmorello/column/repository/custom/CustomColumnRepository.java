package talmo5.talmorello.column.repository.custom;

public interface CustomColumnRepository {

  public void addOneToColumnOrders(Long boardId, Long columnId, int newOrders, int oldOrders);
  public void subtractOneToColumnOrders(Long boardId, Long columnId, int newOrders, int oldOrders);
  public void fetchJoinColumn(Long columnId);
  public Long orderCount();
  public void subtractOrders(Long boardId, int orders);
}
