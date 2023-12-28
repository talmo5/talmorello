package talmo5.talmorello.user.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import talmo5.talmorello.user.dto.LoginRequestDto;
import talmo5.talmorello.user.dto.SignupRequestDto;
import talmo5.talmorello.user.jwt.JwtUtil;
import talmo5.talmorello.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/normal-users/signup")
    public String signup(@RequestBody SignupRequestDto requestDto){
        userService.signup(requestDto);
        return "redirect:api/normal-users/login";
    }

    @PostMapping("/normal-users/login")
    public String login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res){
        try {
            userService.login(requestDto, res);
            return "로그인 성공! 환영합니다!";

        } catch (Exception e){

            return "redirect:/api/normal-users/login?message=Login failed";

        }
    }

}