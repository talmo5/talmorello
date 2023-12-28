package talmo5.talmorello.user.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import talmo5.talmorello.user.dto.LoginRequestDto;
import talmo5.talmorello.user.dto.SignupRequestDTO;
import talmo5.talmorello.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/normal-users/signup")
    public ResponseEntity<SignupRequestDTO.Response> signup(
            @RequestBody SignupRequestDTO.Request requestDTO) {

        SignupRequestDTO.Response responseDTO = userService.signup(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/normal-users/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto requestDto,
            HttpServletResponse res) {

        userService.login(requestDto, res);

        return ResponseEntity.ok("로그인 성공! 환영합니다!");
    }

}