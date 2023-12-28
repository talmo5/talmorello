package talmo5.talmorello.column.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.column.entity.Column;

public class ChangeColumnDTO {

  public record Request(
          @NotBlank Integer orders
  ) {

    public Column toEntity(Board board, int orders) {
      return Column.builder()
              .board(board)
              .orders(orders)
              .build();
    }
  }

  @Builder
  public record Response(Long columnId,
                         Integer orders) {

    public static ChangeColumnDTO.Response from(Column column) {
      return ChangeColumnDTO.Response.builder()
              .columnId(column.getId())
              .orders(column.getOrders())
              .build();
    }

  }
}

