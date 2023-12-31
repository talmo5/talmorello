package talmo5.talmorello.column.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import talmo5.talmorello.column.entity.Column;

public record ModifyColumnDTO (
          @NotBlank @Size(max = 50) String columnTitle
  ) {

}
