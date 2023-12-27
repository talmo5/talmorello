package talmo5.talmorello.column.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import talmo5.talmorello.column.entity.Column;

public interface ColumnRepository extends JpaRepository<Column, Long> {

  List<Column> findTopByOrderByOrders();

}
