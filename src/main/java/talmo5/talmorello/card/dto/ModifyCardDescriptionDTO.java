package talmo5.talmorello.card.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ModifyCardDescriptionDTO(
        @NotBlank @Size(max = 5000) String cardDescription
) {

}
