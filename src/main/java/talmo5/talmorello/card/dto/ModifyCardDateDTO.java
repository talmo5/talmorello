package talmo5.talmorello.card.dto;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public record ModifyCardDateDTO(
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startDate,
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dueDate) {

}
