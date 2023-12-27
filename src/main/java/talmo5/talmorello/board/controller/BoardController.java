package talmo5.talmorello.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talmo5.talmorello.board.dto.BoardRequestDto;
import talmo5.talmorello.board.dto.BoardResponseDto;
import talmo5.talmorello.board.service.BoardService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardResponseDto> postBoard(@RequestBody BoardRequestDto requestDto/*, @AuthenticationPrincipal UserDetailsImpl userDetails*/){
        return ResponseEntity.status(HttpStatus.OK).body(boardService.postBoard(requestDto/*, userDetails*/));
    }
}