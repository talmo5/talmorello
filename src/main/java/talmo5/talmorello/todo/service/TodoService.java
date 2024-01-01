package talmo5.talmorello.todo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talmo5.talmorello.board.entity.Board;
import talmo5.talmorello.boarduser.validator.BoardUserValidator;
import talmo5.talmorello.card.entity.Card;
import talmo5.talmorello.card.service.CardService;
import talmo5.talmorello.global.exception.board.BoardNotFoundException;
import talmo5.talmorello.global.exception.todo.TodoNotFoundException;
import talmo5.talmorello.todo.dto.EditTodoDTO;
import talmo5.talmorello.todo.dto.RegisterTodoDTO;
import talmo5.talmorello.todo.entity.Todo;
import talmo5.talmorello.todo.repository.TodoRepository;
import talmo5.talmorello.user.entity.User;
import talmo5.talmorello.user.service.UserService;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final CardService cardService;

    private final UserService userService;

    private final TodoRepository todoRepository;

    private final BoardUserValidator boardUserValidator;

    @Transactional
    public RegisterTodoDTO.Response registerTodo(
            RegisterTodoDTO.Request requestDTO, Long cardId, Long userId) {

        Card card = cardService.findById(cardId);
        Board board = cardService.getBoardByCardId(cardId);
        User user = userService.findById(userId);

        boardUserValidator.validateBoardUser(board, user);

        Todo todo = requestDTO.toEntity(card);

        Todo savedTodo = todoRepository.save(todo);

        return RegisterTodoDTO.Response.from(savedTodo);
    }

    @Transactional
    public void editTodo(EditTodoDTO requestDTO, Long todoId, Long userId) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(TodoNotFoundException::new);
        Board board = todoRepository.findBoardByTodoId(todoId)
                .orElseThrow(BoardNotFoundException::new);
        User user = userService.findById(userId);

        boardUserValidator.validateBoardUser(board, user);

        todo.updateTodo(requestDTO.todoContent());
    }

    @Transactional
    public void checkTodo(Long todoId, Long userId) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(TodoNotFoundException::new);
        Board board = todoRepository.findBoardByTodoId(todoId)
                .orElseThrow(BoardNotFoundException::new);
        User user = userService.findById(userId);

        boardUserValidator.validateBoardUser(board, user);

        todo.check();
    }

    @Transactional
    public void uncheckTodo(Long todoId, Long userId) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(TodoNotFoundException::new);
        Board board = todoRepository.findBoardByTodoId(todoId)
                .orElseThrow(BoardNotFoundException::new);
        User user = userService.findById(userId);

        boardUserValidator.validateBoardUser(board, user);

        todo.uncheck();
    }

    @Transactional
    public void deleteTodo(Long todoId, Long userId) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(TodoNotFoundException::new);
        Board board = todoRepository.findBoardByTodoId(todoId)
                .orElseThrow(BoardNotFoundException::new);
        User user = userService.findById(userId);

        boardUserValidator.validateBoardUser(board, user);

        todoRepository.delete(todo);
    }
}
