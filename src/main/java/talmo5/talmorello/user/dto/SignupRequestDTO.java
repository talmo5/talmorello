package talmo5.talmorello.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import talmo5.talmorello.user.entity.User;

@Getter
@Setter
public class SignupRequestDto {

    private String username;
    private String password;
    private String email;
}
