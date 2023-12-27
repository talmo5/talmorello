package talmo5.talmorello.column.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import talmo5.talmorello.column.dto.CreateColumnDTO;
import talmo5.talmorello.column.dto.UpdateColumnDTO;
import talmo5.talmorello.column.service.ColumnService;
import talmo5.talmorello.user.entity.User;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/column")
public class ColumnController {

  private final ColumnService columnService;

  private User user = User.builder()
          .id(1L)
          .email("sxxdxh2@gmail.com")
          .username("dahee")
          .build();

  // 컬럼 생성
  @PostMapping("")
  public ResponseEntity<String> createColumn(
          @RequestBody CreateColumnDTO createColumnDTO
  ) {
    return columnService.createColumn(createColumnDTO, user.getId());
  }

  // 컬럼 이름 수정
  @PatchMapping("/{columnId}")
  public ResponseEntity<String> updateColumnName(
          @PathVariable Long columnId,
          @RequestBody UpdateColumnDTO updateColumnDTO
  ) {
    return columnService.updateColumnName(columnId, user.getId(), updateColumnDTO);
  }

}
