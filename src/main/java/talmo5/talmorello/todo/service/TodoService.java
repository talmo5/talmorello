package talmo5.talmorello.todo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.card.service.CardService;
import talmo5.talmorello.global.exception.todo.TodoNotFoundException;
import talmo5.talmorello.todo.dto.EditTodoDTO;
import talmo5.talmorello.todo.dto.RegisterTodoDTO;
import talmo5.talmorello.todo.entity.Todo;
import talmo5.talmorello.todo.repository.TodoRepository;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final CardService cardService;

    private final TodoRepository todoRepository;

    @Transactional
    public RegisterTodoDTO.Response registerTodo(
            RegisterTodoDTO.Request requestDTO, Long cardId, Long userId) {

//        Card card = cardService.findById(cardId);
        Card card = Card.builder().build();

        Todo todo = requestDTO.toEntity(card);

        Todo savedTodo = todoRepository.save(todo);

        return RegisterTodoDTO.Response.from(savedTodo);
    }

    @Transactional
    public void editTodo(EditTodoDTO requestDTO, Long todoId, Long userId) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(TodoNotFoundException::new);

        todo.updateTodo(requestDTO.todoContent());
    }

    @Transactional
    public void checkTodo(Long todoId, Long userId) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(TodoNotFoundException::new);

        todo.check();
    }
}
