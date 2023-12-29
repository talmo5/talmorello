package talmo5.talmorello.column.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import talmo5.talmorello.board.constant.BoardColor;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.column.dto.CreateColumnDTO;
import talmo5.talmorello.column.dto.CreateColumnDTO.Response;
import talmo5.talmorello.column.entity.Column;
import talmo5.talmorello.column.repository.ColumnRepository;
import talmo5.talmorello.global.exception.column.ColumnNotFoundException;
import talmo5.talmorello.global.exception.column.InvalidNewOrdersException;

@Service
@RequiredArgsConstructor
public class ColumnService {

  private final ColumnRepository columnRepository;
  private static final int ORDER_MIN_VALUE = 1;

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
  public void modifyColumnName(Long columnId, String columnTitle) {

    Column column = getColumn(columnId);

    column.modifyColumnName(columnTitle);

  }

  @Transactional
  public void changeColumn(Long columnId, int newOrders) {

    Column column = getColumn(columnId);

    columnRepository.fetchJoinColumn(columnId);

    int oldOrders = column.getOrders();


    if(newOrders > columnRepository.orderCount() || newOrders < ORDER_MIN_VALUE) {
      throw new InvalidNewOrdersException();
    }


    if (newOrders > oldOrders) {
      columnRepository.subtractOneToColumnOrders(column.getBoard().getId(), columnId, newOrders, oldOrders);
    } else {
      columnRepository.addOneToColumnOrders(column.getBoard().getId(), columnId, newOrders, oldOrders);
    }
    column.changeOrders(newOrders);
  }

  @Transactional
  public void deleteColumn(Long columnId) {

    Column column = getColumn(columnId);

    columnRepository.fetchJoinColumn(columnId);

    columnRepository.subtractOrders(column.getBoard().getId(), column.getOrders());
    columnRepository.deleteById(columnId);
  }


  public Column getColumn(Long columnId) {
    return columnRepository.findById(columnId).orElseThrow(
            ColumnNotFoundException::new);
    }

  }

