package talmo5.talmorello.column.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import talmo5.talmorello.column.dto.CreateColumnDTO;
import talmo5.talmorello.column.dto.UpdateColumnDTO;
import talmo5.talmorello.column.entity.Column;
import talmo5.talmorello.column.repository.ColumnRepository;
import talmo5.talmorello.global.exception.column.ColumnNotFoundException;
import talmo5.talmorello.global.exception.common.ErrorCode;

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

  @Transactional
  public ResponseEntity<String> updateColumnName(
          Long columnId,
          Long userId, //
          UpdateColumnDTO updateColumnDTO) {

    Column column = getColumn(columnId);

    column.updateTitle(updateColumnDTO.getTitle());

    return new ResponseEntity<>("컬럼 이름을 수정했습니다.", HttpStatus.OK);

  }

  public Column getColumn(Long columnId) {
    return columnRepository.findById(columnId).orElseThrow(
            () -> {
              throw new ColumnNotFoundException(ErrorCode.NOT_FOUND_COLUMN_EXCEPTION);
            });
  }

}
