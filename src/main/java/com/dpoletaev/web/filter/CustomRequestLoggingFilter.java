package com.dpoletaev.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomRequestLoggingFilter extends OncePerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(CustomRequestLoggingFilter.class);

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long start = System.nanoTime();

        LoggingRequest loggingRequest = new LoggingRequest(request);
        LoggingResponse loggingResponse = new LoggingResponse(response);

        logger.info("Received request with payload: {}", loggingRequest.getRequestBody());

        filterChain.doFilter(loggingRequest, loggingResponse);

        logger.info("Request response is {}", loggingResponse.getBody());

        response.getWriter().write(loggingResponse.getBody());

        logger.info("Request execution time is {} ms", (System.nanoTime() - start) / 100000);
    }


}
