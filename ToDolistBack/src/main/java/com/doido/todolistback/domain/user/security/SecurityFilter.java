package com.doido.todolistback.domain.user.security;

import com.doido.todolistback.domain.user.servicies.TokenService;
import com.doido.todolistback.infra.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = this.recoverToken(request);

        if (token != null) {

            var login = tokenService.ValidationToken(token);

            if (!login.isEmpty()) {
                UserDetails user = userRepository.findByEmail(login);

                var authentication = new UsernamePasswordAuthenticationToken(login, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeder = request.getHeader("Authorization");
        if (authHeder == null || !authHeder.startsWith("Bearer "))  return null;

        return authHeder.replaceFirst("Bearer ", "");
    }
}
