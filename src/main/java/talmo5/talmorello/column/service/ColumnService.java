package talmo5.talmorello.column.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import talmo5.talmorello.board.constant.BoardColor;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.column.dto.CreateColumnDTO;
import talmo5.talmorello.column.dto.CreateColumnDTO.Response;
import talmo5.talmorello.column.dto.ModifyColumnDTO;
import talmo5.talmorello.column.entity.Column;
import talmo5.talmorello.column.repository.ColumnRepository;
import talmo5.talmorello.global.exception.column.ColumnNotFoundException;

@Service
@RequiredArgsConstructor
public class ColumnService {

  private final ColumnRepository columnRepository;

  private Board board = Board.builder()
          .id(1L)
          .title("helloworld")
          .content("hi")
          .boardColor(BoardColor.PINK)
          .build();


  public Response createColumn(Long boardId, CreateColumnDTO.Request createColumnDTO) {

    int orders = getLastOrders();
    Column column = createColumnDTO.toEntity(createColumnDTO.columnTitle(), board, orders + 1);

    columnRepository.save(column);

    return CreateColumnDTO.Response.from(column);
  }

  private int getLastOrders() {

    Integer orders = columnRepository.getLastOrders();
    if (orders == null) {
      return 0;
    }
    return orders;
  }

  @Transactional
  public ModifyColumnDTO.Response modifyColumnName(Long columnId, ModifyColumnDTO.Request modifyColumnDTO) {

    Column column = getColumn(columnId);

    column.modifyColumnName(modifyColumnDTO.columnTitle());

    return ModifyColumnDTO.Response.from(column);

  }

  @Transactional
  public void changeColumn(Long columnId, int columnOrders) {

    Column column = getColumn(columnId);

    int oldOrders = column.getOrders();
    int newOrders = columnOrders;

    if (newOrders > oldOrders) {
      columnRepository.addOneToColumnOrders(board, newOrders, oldOrders);
    } else {
      columnRepository.subtractOneToColumnOrders(board, newOrders, oldOrders);
    }
    column.changeOrders(columnOrders);
  }

  public Column getColumn(Long columnId) {
    return columnRepository.findById(columnId).orElseThrow(
            ColumnNotFoundException::new);
    }

  }

