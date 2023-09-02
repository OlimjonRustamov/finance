package com.example.financetest.config;

import com.example.financetest.error.CustomError;
import com.example.financetest.user.User;
import com.example.financetest.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

    UserRepository userRepository;
    MyUserDetailsService myUserDetailsService;

    public JwtFilter(UserRepository userRepository, MyUserDetailsService myUserDetailsService) {
        this.userRepository = userRepository;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("x-access-token");
        if(request.getServletPath().equals("/api/auth/login") || request.getServletPath().contains("/api/image/view/")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (token == null) {
            response.setStatus(401);
            response.setContentType("application/json");
            String json = new ObjectMapper().writer().writeValueAsString(new CustomError("Token is required", 401));
            response.getWriter().write(json);
            return;
        }
        Optional<User> optionalUser = userRepository.findByAccessToken(token);
        if (optionalUser.isPresent()) {
            response.setStatus(401);
            response.setContentType("application/json");
            String json = new ObjectMapper().writer().writeValueAsString(new CustomError("Token expired or does not exist", 403));
            response.getWriter().write(json);
            return;
        }
        User user = optionalUser.get();
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(user.getUsername());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}
