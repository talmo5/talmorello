package talmo5.talmorello.column.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Builder;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.column.entity.Column;


public class CreateColumnDTO {

  public record Request(
          @NotBlank @Size(max = 50) String columnTitle
  ) {

    public Column toEntity(String columnTitle, Board board, int orders) {
      return Column.builder()
              .title(columnTitle)
              .board(board)
              .orders(orders)
              .build();
    }
  }

  @Builder
  public record Response(Long columnId, String columnTitle, LocalDateTime createdAt) {

    public static Response of(Column column) {

      return Response.builder()
              .columnId(column.getId())
              .columnTitle(column.getTitle())
              .createdAt(column.getCreatedAt())
              .build();
    }
  }
}

