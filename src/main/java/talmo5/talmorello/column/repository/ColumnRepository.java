package talmo5.talmorello.column.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import talmo5.talmorello.column.entity.Column;
import talmo5.talmorello.column.repository.custom.CustomColumnRepository;

public interface ColumnRepository extends JpaRepository<Column, Long>, CustomColumnRepository {
  @Query("SELECT c.orders FROM Column c ORDER BY c.orders DESC LIMIT 1")
  Integer getLastOrders();





}
