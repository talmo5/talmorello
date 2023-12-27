package talmo5.talmorello.column.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import talmo5.talmorello.column.dto.CreateColumnDTO;
import talmo5.talmorello.column.entity.Column;
import talmo5.talmorello.column.repository.ColumnRepository;

@Service
@RequiredArgsConstructor
public class ColumnService {

  private final ColumnRepository columnRepository;

  @Transactional
  public ResponseEntity<String> createColumn(CreateColumnDTO columnRequestDTO, Long userId) {

    List<Column> columnList = columnRepository.findTopByOrderByOrders();

    Integer orders =
            (columnList.size() == 0) ? 1 : columnList.get((columnList.size() - 1)).getOrders() + 1;

    Column column = new Column(columnRequestDTO.getTitle(), orders);

    columnRepository.save(column);

    return new ResponseEntity<>("컬럼을 생성했습니다.", HttpStatus.OK);
  }


}
