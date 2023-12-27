package talmo5.talmorello.user.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import talmo5.talmorello.global.exception.user.UserAlreadyExistException;
import talmo5.talmorello.user.dto.SignupRequestDto;
import talmo5.talmorello.user.entity.User;
import talmo5.talmorello.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequestDto requestDto){
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String email = requestDto.getEmail();

//        // 회원 중복 확인
//        try {
//            User user = userRepository.findByUsername(username)
//                    .orElseThrow(() -> new UserAlreadyExistException("이미 존재하는 이름입니다."));
//        } catch (UserAlreadyExistException e) {
//
//        }

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("사용중인 username입니다.");
        }

        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("이미 사용중인 email입니다.");
        }

//        try {
//            User user = userRepository.findByEmail(email)
//                    .orElseThrow(() -> new IllegalArgumentException("중복된 Email 입니다."));
//        } catch (IllegalArgumentException e) {
//
//        }

        User user = new User(username, password, email);
        userRepository.save(user);
    }

}
