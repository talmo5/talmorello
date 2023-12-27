package talmo5.talmorello.column.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ColumnRequestDTO {
  private Long id;
  private String title;

}
