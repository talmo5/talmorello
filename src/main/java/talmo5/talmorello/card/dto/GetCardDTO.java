package talmo5.talmorello.card.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.comment.entity.Comment;
import talmo5.talmorello.todo.constant.TodoStatus;
import talmo5.talmorello.todo.entity.Todo;

@Builder
public record GetCardDTO(
        Long cardId,
        String cardTitle,
        String cardDescription,
        String username,
        LocalDateTime startDate,
        LocalDateTime dueDate,
        LocalDateTime createdAt,
        List<GetCommentDTO> commentList,
        List<GetTodoDTO> todoList) {

    public static GetCardDTO from(Card card) {

        return GetCardDTO.builder()
                .cardId(card.getId())
                .cardTitle(card.getTitle())
                .cardDescription(card.getContent())
                .username(card.getUser().getUsername())
                .startDate(card.getStartDate())
                .dueDate(card.getDueDate())
                .createdAt(card.getCreatedAt())
                .commentList(card.getCommentList().stream().map(GetCommentDTO::from).toList())
                .todoList(card.getTodoList().stream().map(GetTodoDTO::from).toList())
                .build();
    }

    @Builder
    private record GetCommentDTO(Long id, String content, String username) {

        public static GetCommentDTO from(Comment comment) {

            return GetCommentDTO.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .username(comment.getUser().getUsername())
                    .build();
        }
    }

    @Builder
    private record GetTodoDTO(Long id, String content, TodoStatus todoStatus) {

        public static GetTodoDTO from(Todo todo) {

            return GetTodoDTO.builder()
                    .id(todo.getId())
                    .content(todo.getContent())
                    .todoStatus(todo.getTodoStatus())
                    .build();
        }
    }

}
