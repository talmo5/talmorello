package talmo5.talmorello.board.dto;

import lombok.Builder;
import talmo5.talmorello.user.entity.User;

@Builder
public record GetBoardUserDto(
        Long userId,
        String email,
        String username
) {

    public static GetBoardUserDto from(User user) {
        return GetBoardUserDto.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }

}
