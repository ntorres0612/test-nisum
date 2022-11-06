package com.example.test.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.test.model.User;
import com.example.test.service.IUserService;
import com.example.test.service.impl.UserServiceImpl;
import com.example.test.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        System.out.println(":::::::::::::::::::::::requestTokenHeader:::::::::::::::::::::::::");
        System.out.println(requestTokenHeader);

        String email = null;
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                email = jwtTokenUtil.getEmailByToken(jwtToken);
                System.out.println(":::::::::::::::::::::::email:::::::::::::::::::::::::");
                System.out.println(email);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
            System.out.println(":::::::::::JWT Token does not begin with Bearer String::::::::::::");
        }

        // Once we get the token validate it.
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            System.out.println(":::::::::::Yes::::::::::::");
            User user = this.iUserService.findByEmail(email);

            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtTokenUtil.validateToken(jwtToken, user)) {

                System.out.println(":::::::::::Valiad::::::::::::");
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        user, null, null);
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                System.out.println(":::::::::::Not Valid::::::::::::");
            }
        }
        chain.doFilter(request, response);
    }

}