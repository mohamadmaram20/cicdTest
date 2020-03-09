package com.cyberoxi.hstpfacilities.configurations.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cyberoxi.hstpfacilities.models.requests.LoginRequest;
import com.cyberoxi.hstpfacilities.models.responses.LoginResponse;
import com.cyberoxi.hstpfacilities.services.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/30/2020
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private LoginService loginService;

    JwtAuthenticationFilter(AuthenticationManager authenticationManager, LoginService loginService) {
        this.authenticationManager = authenticationManager;
        this.loginService = loginService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            LoginRequest loginModel = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(loginModel.getUsername(), loginModel.getPassword(), new ArrayList<>());
            return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (IOException e) {
            // TODO: 3/2/2020 handle this exception in ControllerAdvice
            throw new BadCredentialsException("Wrong username or password");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        String token = JWT.create()
                .withSubject(((User) authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);

        LoginResponse loginResponse = loginService.login(((User) authResult.getPrincipal()).getUsername());
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonResponseString = objectWriter.writeValueAsString(loginResponse);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(jsonResponseString);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
