package com.diego.supermercado.web.security.filter;

import com.diego.supermercado.domain.service.MarketUserDetailsService;
import com.diego.supermercado.web.security.JWTUtil;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilterRequest extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private MarketUserDetailsService marketUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            String jwt = authorizationHeader.substring(7);
            String username = jwtUtil.extractUsernameFromToken(jwt);

            if (username!= null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = marketUserDetailsService.loadUserByUsername(username);

                if (jwtUtil.validateToken(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

            }
        }
        filterChain.doFilter(request,response);
    }
}
