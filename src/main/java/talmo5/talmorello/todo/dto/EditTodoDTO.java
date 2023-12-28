package talmo5.talmorello.todo.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EditTodoDTO(@NotBlank @Length(max = 500) String todoContent) {

}
