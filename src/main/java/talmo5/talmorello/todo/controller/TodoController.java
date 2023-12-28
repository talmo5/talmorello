package talmo5.talmorello.todo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
