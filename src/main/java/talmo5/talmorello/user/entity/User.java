package talmo5.talmorello.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import talmo5.talmorello.auditing.BaseTime;
import talmo5.talmorello.user.constant.SocialType;

@Getter
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(length = 100)
    private String password;

    private SocialType socialType;

    private Long kakaoId;

    public static User createUser(String email, String username, String password) {
        return User.builder()
                .email(email)
                .username(username)
                .password(password)
                .build();
    }
    public static User createKakaoUser(String username, String password, String email, Long kakaoId) {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .kakaoId(kakaoId)
                .socialType(SocialType.KAKAO)
                .build();
    }

    public User kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }


}
