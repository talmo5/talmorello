package talmo5.talmorello.user.service;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import talmo5.talmorello.user.dto.LoginRequestDto;
import talmo5.talmorello.user.dto.SignupRequestDto;
import talmo5.talmorello.user.entity.User;
import talmo5.talmorello.user.jwt.JwtUtil;
import talmo5.talmorello.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void signup(SignupRequestDto requestDto){
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();


        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("사용중인 username입니다.");
        }

        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("이미 사용중인 email입니다.");
        }

        User user = User.createUser(email, username, password);
        userRepository.save(user);
    }

    public void login(LoginRequestDto requestDto, HttpServletResponse res){
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new IllegalArgumentException("일치하는 사용자가 없습니다!")
        );


        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("다시 입력하세요! 비밀번호가 일치하지 않습니다!");
        }

        String token = jwtUtil.createToken(user.getUsername());
        jwtUtil.addJwtToCookie(token, res);

    }



}
