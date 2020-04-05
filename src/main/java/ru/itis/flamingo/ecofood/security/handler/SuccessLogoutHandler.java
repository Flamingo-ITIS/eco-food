package ru.itis.flamingo.ecofood.security.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import ru.itis.flamingo.ecofood.security.jwt.JwtAuthenticationFilter;
import ru.itis.flamingo.ecofood.security.jwt.JwtService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SuccessLogoutHandler implements LogoutSuccessHandler {

    private final JwtService jwtService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var token = jwtService.resolveToken(request);
        if (token != null) {
            jwtService.addExpireToken(token);
            request.getSession().invalidate();
        } else {
            throw new RuntimeException("Token is not specify");
        }
    }
}
