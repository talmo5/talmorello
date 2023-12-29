package talmo5.talmorello.card.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ModifyCardTitleDTO(
        @NotBlank @Size(max = 50) String cardTitle) {

}
