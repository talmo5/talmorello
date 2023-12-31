package talmo5.talmorello.todo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.todo.constant.TodoStatus;
import talmo5.talmorello.todo.entity.Todo;

public class RegisterTodoDTO {

    public record Request(@NotBlank @Length(max = 500) String todoContent) {

        public Todo toEntity(Card card) {
            return Todo.builder()
                    .content(todoContent)
                    .todoStatus(TodoStatus.UNCHECK)
                    .card(card)
                    .build();
        }

    }

    @Builder
    public record Response(Long todoId, String todoContent, TodoStatus todoStatus) {

        public static Response from(Todo todo) {
            return Response.builder()
                    .todoId(todo.getId())
                    .todoContent(todo.getContent())
                    .todoStatus(todo.getTodoStatus())
                    .build();
        }
    }

}
