package talmo5.talmorello.comment.dto;

import jakarta.validation.constraints.NotBlank;

public record EditCommentDTO(
        @NotBlank String commentContent
) {}
