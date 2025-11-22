package com.syntech.sustentabilidadesocial.sustentabilidade_social.middlewares;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.syntech.sustentabilidadesocial.sustentabilidade_social.utils.JWTUtils;

import java.io.IOException;
import java.util.List;

@Component
public class JwtMiddleware extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                String userId = jwtUtils.validateToken(token);

                if (userId == null) {
                    throw new BadCredentialsException("Token inválido ou expirado");
                }

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userId, null, List.of());

                SecurityContextHolder.getContext().setAuthentication(auth);

                request.setAttribute("userId", userId);
            } catch (Exception ex) {
                ex.printStackTrace();

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                response.setContentType("application/json");

                response.getWriter().write("""
                        {"erro": "Token inválido ou expirado"}
                        """);

                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
