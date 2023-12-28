package talmo5.talmorello.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import talmo5.talmorello.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
