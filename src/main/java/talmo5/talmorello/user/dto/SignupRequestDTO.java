package talmo5.talmorello.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import talmo5.talmorello.user.entity.User;

@Getter
@Setter
public class SignupRequestDTO {

    public record Request(
            String username,
            String password,
            String email) {


    }

    @Builder
    public record Response(
            Long userId,
            String email,
            String username
    ){
        public static Response from(User user){
            return Response.builder()
                    .userId(user.getId())
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .build();
        }
    }
}
