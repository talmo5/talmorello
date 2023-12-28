package talmo5.talmorello.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talmo5.talmorello.board.dto.PostBoardDTO;
import talmo5.talmorello.board.service.BoardService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<PostBoardDTO.Response> postBoard(@Valid @RequestBody PostBoardDTO.Request requestDto){
        return ResponseEntity.ok().body(boardService.postBoard(requestDto));
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity<?> patchBoard(@Valid @RequestBody PostBoardDTO.Request requestDto, @PathVariable Long boardId){
        boardService.patchBoard(requestDto, boardId);
        return ResponseEntity.ok().body("수정 성공");
    }
}