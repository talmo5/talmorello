package talmo5.talmorello.user.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import talmo5.talmorello.user.jwt.JwtUtil;

@Slf4j(topic = "JwtAuthFilter")
@Component
@RequiredArgsConstructor
@Order(1)
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {

            String tokenValue = jwtUtil.getTokenFromRequest(req);

        if (StringUtils.hasText(tokenValue)) {
            throw new IllegalArgumentException("Not Found Token");
        }

                String token = jwtUtil.substringToken(tokenValue);

                if (!jwtUtil.validateToken(token)) {
                    throw new IllegalArgumentException("Token Error");
                }

                String username = jwtUtil.getUsernameFromToken(token);

                User user = userRepository.findByUsername(username).orElseThrow(() ->
                        new NullPointerException("Not Found User")
                );

                req.setAttribute("user", user);
                filterChain.doFilter(req, res);
            } else {
                throw new IllegalArgumentException("Not Found Token");
            }
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        String[] excludePath = {"/api/normal-users/signup", "/api/normal-users/login"};

        String url = request.getRequestURI();

        return Arrays.stream(excludePath).anyMatch(url::startsWith);
    }
}