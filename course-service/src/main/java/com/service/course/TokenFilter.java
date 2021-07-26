package com.service.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter extends OncePerRequestFilter
{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        if( !isTokenValid(request) )
        {
            throw new HttpClientErrorException( HttpStatus.UNAUTHORIZED );
        }
        filterChain.doFilter(request, response);
    }

    private boolean isTokenValid( HttpServletRequest request )
    {
        String token = request.getHeader("token");
        RestTemplate restTemplate = new RestTemplate();

        try
        {
            ResponseEntity responseEntity = restTemplate.postForEntity("http://localhost:8080/token/validate", token, String.class);
            if( !responseEntity.getStatusCode().is2xxSuccessful() )
            {
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }
}
