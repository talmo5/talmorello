package talmo5.talmorello.column.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import talmo5.talmorello.column.dto.ColumnRequestDTO;
import talmo5.talmorello.column.service.ColumnService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/column")
public class ColumnController {

  private final ColumnService columnService;

  // 컬럼 생성
  @PostMapping("")
  public ResponseEntity<String> createColumn(
          @RequestBody ColumnRequestDTO columnRequestDTO,
          @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    return columnService.createColumn(columnRequestDTO, userDetails.getUser());
  }

}
