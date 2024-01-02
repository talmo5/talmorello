package talmo5.talmorello.user.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import talmo5.talmorello.global.exception.common.BusinessException;
import talmo5.talmorello.global.exception.common.ErrorResponse;
import talmo5.talmorello.global.exception.jwt.JwtClaimsEmptyException;
import talmo5.talmorello.global.exception.jwt.JwtExpiredException;
import talmo5.talmorello.global.exception.jwt.JwtInvalidException;
import talmo5.talmorello.global.exception.jwt.JwtNotFoundException;
import talmo5.talmorello.global.exception.jwt.JwtUnsupportedEncodingException;
import talmo5.talmorello.global.exception.jwt.JwtUnsupportedException;
import talmo5.talmorello.global.exception.jwt.UnsupportedGrantTypeException;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
            FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(req, res);
        } catch (JwtInvalidException | JwtExpiredException | JwtClaimsEmptyException |
                 JwtNotFoundException | JwtUnsupportedEncodingException |
                 JwtUnsupportedException | UnsupportedGrantTypeException e) {
            sendErrorMessage(res, e);
        }
    }

    private void sendErrorMessage(HttpServletResponse response, BusinessException e)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(e.getStatus());
        response.getWriter().write(objectMapper.writeValueAsString(
                ErrorResponse.of(HttpStatus.valueOf(e.getStatus()), e.getMessage())));
    }

}


