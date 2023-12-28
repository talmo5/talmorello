package talmo5.talmorello.column.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import talmo5.talmorello.column.dto.CreateColumnDTO;
import talmo5.talmorello.column.service.ColumnService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/columns")
public class ColumnController {

  private final ColumnService columnService;

  @PostMapping("/boards/{boardId}")
  public ResponseEntity<CreateColumnDTO.Response> createColumn(
          @PathVariable Long boardId,
          @RequestBody @Valid CreateColumnDTO.Request createColumnDTO
          // ,@AuthenticationPrincipal UserDetailsImpl userDetails
  ) {

    CreateColumnDTO.Response responseDTO = columnService.createColumn(boardId, createColumnDTO);
    // (boardId, createColumnDTO, userDetails.getUser());

    return ResponseEntity.ok(responseDTO);
  }



}
