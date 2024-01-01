package talmo5.talmorello.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talmo5.talmorello.board.dto.ModifyBoardDTO;
import talmo5.talmorello.board.dto.PostBoardDTO;
import talmo5.talmorello.board.service.BoardService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<?> postBoard(@Valid @RequestBody PostBoardDTO.Request requestDto){
        boardService.postBoard(requestDto);
        return ResponseEntity.ok().body("생성 성공");
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity<?> patchBoard(@Valid @RequestBody ModifyBoardDTO.Request requestDto, @PathVariable Long boardId){
        boardService.patchBoard(requestDto, boardId);
        return ResponseEntity.ok().body("수정 성공");
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long boardId){
        boardService.deleteBoard(boardId);
        return ResponseEntity.ok().body("삭제 성공");
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<?> getBoard(@PathVariable Long boardId){
        return ResponseEntity.ok().body(boardService.getBoard(boardId));
    }

    @PostMapping("/{boardId}/user/{inviteUserId}")
    public ResponseEntity<?> inviteUser(@PathVariable Long boardId, @PathVariable Long inviteUserId){
        boardService.inviteUser(boardId, inviteUserId);
        return ResponseEntity.ok().body("초대 성공");
    }
}