package talmo5.talmorello.comment.controller;

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
import talmo5.talmorello.comment.dto.EditCommentDTO;
import talmo5.talmorello.comment.dto.RegisterCommentDTO;
import talmo5.talmorello.comment.service.CommentService;
import talmo5.talmorello.global.argumentresolver.LoginUserId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/card-comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/card/{cardId}")
    public ResponseEntity<?> registerComment(
            @PathVariable Long cardId, @Valid @RequestBody RegisterCommentDTO.Request requestDTO,
            @LoginUserId Long userId) {

        RegisterCommentDTO.Response responseDTO =
                commentService.registerComment(cardId, requestDTO, userId);

        return ResponseEntity.ok(responseDTO);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<?> editComment(
            @PathVariable Long commentId,
            @Valid @RequestBody EditCommentDTO requestDTO, @LoginUserId Long userId) {

        commentService.editComment(commentId, requestDTO, userId);

        return ResponseEntity.ok("댓글 수정 성공");
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId, Long userId) {

        commentService.deleteComment(commentId, userId);

        return ResponseEntity.ok("댓글 삭제 성공");
    }

}
