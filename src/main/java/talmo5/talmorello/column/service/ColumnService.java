package talmo5.talmorello.column.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talmo5.talmorello.board.constant.BoardColor;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.column.dto.CreateColumnDTO;
import talmo5.talmorello.column.dto.CreateColumnDTO.Request;
import talmo5.talmorello.column.dto.CreateColumnDTO.Response;
import talmo5.talmorello.column.entity.Column;
import talmo5.talmorello.column.repository.ColumnRepository;

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


  public Response createColumn(Long boardId, Request createColumnDTO) {

    int orders = getLastOrders();
    Column column = createColumnDTO.toEntity(createColumnDTO.columnTitle(), board, orders + 1);

    columnRepository.save(column);

    return CreateColumnDTO.Response.of(column);
  }

  private int getLastOrders() {

    Integer orders = columnRepository.getLastOrders();
    if (orders == null) {
      return 0;
    }
    return orders;
  }

}
