package com.cyberoxi.hstpfacilities.configurations.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cyberoxi.hstpfacilities.models.Admin;
import com.cyberoxi.hstpfacilities.repositories.AdminsRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/30/2020
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private AdminsRepository adminsRepository;

    JwtAuthorizationFilter(AuthenticationManager authenticationManager, AdminsRepository adminsRepository) {
        super(authenticationManager);
        this.adminsRepository = adminsRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JwtProperties.HEADER_STRING);
        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtProperties.HEADER_STRING);
        if (token != null) {
            String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(JwtProperties.TOKEN_PREFIX, ""))
                    .getSubject();
            if (username != null) {
                Admin admin = adminsRepository.findByUsername(username).get();
                return new UsernamePasswordAuthenticationToken(
                        username, null, Collections.singleton(
                        new SimpleGrantedAuthority(admin.getRole())));
            }
            return null;
        }
        return null;
    }
}
