package talmo5.talmorello.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import talmo5.talmorello.comment.dto.EditCommentDTO;
import talmo5.talmorello.comment.dto.RegisterCommentDTO;
import talmo5.talmorello.comment.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/card-comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/card/{cardId}")
    public ResponseEntity<?> registerComment(
            @PathVariable Long cardId, @Valid @RequestBody RegisterCommentDTO.Request requestDTO) {

        Long userId = 1L;
        RegisterCommentDTO.Response responseDTO =
                commentService.registerComment(cardId, requestDTO, userId);

        return ResponseEntity.ok(responseDTO);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<?> editComment(
            @PathVariable Long commentId,
            @Valid @RequestBody EditCommentDTO requestDTO) {

        commentService.editComment(commentId, requestDTO);

        return ResponseEntity.ok("댓글 수정 성공");
    }

}
