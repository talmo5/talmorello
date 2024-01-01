package talmo5.talmorello.user.service;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import talmo5.talmorello.global.exception.user.PasswordMismatchedException;
import talmo5.talmorello.global.exception.user.UserAlreadyExistException;
import talmo5.talmorello.global.exception.user.UserNotFoundException;
import talmo5.talmorello.user.dto.LoginRequestDTO;
import talmo5.talmorello.user.dto.SignupRequestDTO;
import talmo5.talmorello.user.entity.User;
import talmo5.talmorello.user.jwt.JwtUtil;
import talmo5.talmorello.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public SignupRequestDTO.Response signup(SignupRequestDTO.Request requestDto) {
        String username = requestDto.username();
        String password = passwordEncoder.encode(requestDto.password());
        String email = requestDto.email();

        validateDuplication(username, email);

        User user = User.createUser(email, username, password);
        User savedUser = userRepository.save(user);

        return SignupRequestDTO.Response.from(savedUser);
    }

    public void validateDuplication(String username, String email) {

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new UserAlreadyExistException();
        }

        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new UserAlreadyExistException();
        }
    }

    public void login(LoginRequestDTO requestDto, HttpServletResponse res) {
        String username = requestDto.username();
        String password = requestDto.password();

        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordMismatchedException();
        }

        String token = jwtUtil.createToken(user.getId(), user.getUsername());
        jwtUtil.addJwtToCookie(token, res);
    }

    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);
  
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

}
