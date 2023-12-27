package talmo5.talmorello.column.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import talmo5.talmorello.column.entity.Column;

@Getter
public class ColumnResponseDTO {
  private Long columnId;
  private String columnTitle;
  private LocalDateTime createdAt;

  public ColumnResponseDTO(Column column) {
    this.columnId = column.getId();
    this.columnTitle = column.getTitle();
    this.createdAt = column.getCreatedAt();
  }

}
