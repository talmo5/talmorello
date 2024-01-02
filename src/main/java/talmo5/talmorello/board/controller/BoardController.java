package talmo5.talmorello.board.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import talmo5.talmorello.board.dto.GetBoardUserDto;
import talmo5.talmorello.board.dto.ModifyBoardDTO;
import talmo5.talmorello.board.dto.PostBoardDTO;
import talmo5.talmorello.board.service.BoardService;
import talmo5.talmorello.global.argumentresolver.LoginUserId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<?> postBoard(@Valid @RequestBody PostBoardDTO.Request requestDto
    ,@LoginUserId Long userId){
        boardService.postBoard(requestDto, userId);
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
    public ResponseEntity<?> inviteBoardUser(@PathVariable Long boardId, @PathVariable Long inviteUserId
    ,@LoginUserId Long userId){

        boardService.inviteBoardUser(boardId, inviteUserId, userId);

        return ResponseEntity.ok().body("초대 성공");
    }

    @DeleteMapping("/{boardId}/user/{deleteUserId}")
    public ResponseEntity<?> deleteBoardUser(@PathVariable Long boardId,
            @PathVariable Long deleteUserId, @LoginUserId Long userId) {

        boardService.deleteBoardUser(boardId, deleteUserId, userId);

        return ResponseEntity.ok().body("삭제 성공");
    }

    @GetMapping("/{boardId}/users")
    public ResponseEntity<?> getBoardUsers(@PathVariable Long boardId) {

        List<GetBoardUserDto> boardUsers = boardService.getBoardUsers(boardId);

        return ResponseEntity.ok(boardUsers);
    }
}