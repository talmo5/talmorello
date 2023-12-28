package talmo5.talmorello.todo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import talmo5.talmorello.todo.dto.EditTodoDTO;
import talmo5.talmorello.todo.dto.RegisterTodoDTO;
import talmo5.talmorello.todo.dto.RegisterTodoDTO.Response;
import talmo5.talmorello.todo.service.TodoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/card/{cardId}")
    public ResponseEntity<?> registerTodo(@PathVariable Long cardId, @Valid @RequestBody
    RegisterTodoDTO.Request requestDTO) {

        Long userId = 1L;
        Response response = todoService.registerTodo(requestDTO, cardId, userId);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<?> editTodo(@PathVariable Long todoId,
            @Valid @RequestBody EditTodoDTO requestDTO) {

        Long userId = 1L;
        todoService.editTodo(requestDTO, todoId, userId);

        return ResponseEntity.ok("할일 내용 수정 성공");
    }

    @PostMapping("{todoId}/check")
    public ResponseEntity<?> checkTodo(@PathVariable Long todoId) {

        Long userId = 1L;
        todoService.checkTodo(todoId, userId);

        return ResponseEntity.ok("할일 체크 성공");
    }

    @PostMapping("{todoId}/uncheck")
    public ResponseEntity<?> uncheckTodo(@PathVariable Long todoId) {

        Long userId = 1L;
        todoService.uncheckTodo(todoId, userId);

        return ResponseEntity.ok("할일 체크 해제 성공");
    }

}
