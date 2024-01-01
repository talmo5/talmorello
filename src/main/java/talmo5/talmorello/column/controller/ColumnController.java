package talmo5.talmorello.column.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import talmo5.talmorello.column.dto.CreateColumnDTO;
import talmo5.talmorello.column.dto.ModifyColumnDTO;
import talmo5.talmorello.column.service.ColumnService;
import talmo5.talmorello.global.argumentresolver.LoginUserId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/columns")
public class ColumnController {

  private final ColumnService columnService;

  @PostMapping("/boards/{boardId}")
  public ResponseEntity<CreateColumnDTO.Response> createColumn(
          @PathVariable Long boardId,
          @RequestBody @Valid CreateColumnDTO.Request createColumnDTO,
          @LoginUserId Long userId
  ) {

    CreateColumnDTO.Response responseDTO = columnService.createColumn(boardId, createColumnDTO, userId);
    // (boardId, createColumnDTO, userDetails.getUser());

    return ResponseEntity.ok(responseDTO);
  }

  // 컬럼 이름 수정
  @PatchMapping("/{columnId}")
  public ResponseEntity<String> modifyColumnName(
          @PathVariable Long columnId,
          @RequestBody @Valid ModifyColumnDTO modifyColumnDTO,
          @LoginUserId Long userId
  ) {
    columnService.modifyColumnName(columnId, modifyColumnDTO.columnTitle(), userId);

    return ResponseEntity.ok("컬럼 이름 수정 성공");
  }

  // 컬럼 이동
  @PatchMapping("/{columnId}/order/{columnOrders}")
  public ResponseEntity<String> changeColumn(
          @PathVariable Long columnId,
          @PathVariable int columnOrders,
          @LoginUserId Long userId
  ) {
    columnService.changeColumn(columnId, columnOrders, userId);

    return ResponseEntity.ok("컬럼 이동 성공");
  }

  // 컬럼 삭제
  @DeleteMapping("/{columnId}")
  public ResponseEntity<String> deleteColumn(
          @PathVariable Long columnId,
          @LoginUserId Long userId
  ) {
    columnService.deleteColumn(columnId, userId);

    return ResponseEntity.ok("컬럼 삭제 성공");
  }

}
