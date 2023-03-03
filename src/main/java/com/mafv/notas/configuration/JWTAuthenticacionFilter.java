package com.mafv.notas.configuration;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mafv.notas.models.Credentials;

public class JWTAuthenticacionFilter extends UsernamePasswordAuthenticationFilter{

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {

            Credentials credentials = new Credentials();

            try {
                credentials = new ObjectMapper().readValue(request.getReader(), Credentials.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword(),
                Collections.emptyList());  
        
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

                UserDetails userDetails = (UserDetails) authResult.getPrincipal();
                String token = TokenUtils.createToken(userDetails.getUsername(), userDetails.getUsername());
                
                response.addHeader("Authorization", "Bearer " + token);

                response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
    

}
