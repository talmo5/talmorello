package talmo5.talmorello.column.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Builder;
import talmo5.talmorello.column.entity.Column;

public class ModifyColumnDTO {

  public record Request(
          @NotBlank @Size(max = 50) String columnTitle
  ) {

  }

  @Builder
  public record Response(Long columnId,
                         String columnTitle
                         ) {

    public static Response from(Column column) {
      return Response.builder()
              .columnId(column.getId())
              .columnTitle(column.getTitle())
              .build();
    }

  }
}
