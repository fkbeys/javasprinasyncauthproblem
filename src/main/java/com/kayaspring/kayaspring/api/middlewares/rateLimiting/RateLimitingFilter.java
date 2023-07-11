package com.kayaspring.kayaspring.api.middlewares.rateLimiting;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class RateLimitingFilter implements jakarta.servlet.Filter {

    private final Bucket bucket;

    @Autowired
    public RateLimitingFilter(Bucket bucket) {
        this.bucket = bucket;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (bucket.tryConsume(1)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {

            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.setContentType("application/json");
            httpResponse.setStatus(429);

            Map<String, Object> anonymousObject = new HashMap<>();
            anonymousObject.put("message", "too many request..");
            anonymousObject.put("isSuccess", false);
            anonymousObject.put("data", "");
            anonymousObject.put("totalItemCount", 0);
            String json = new ObjectMapper().writeValueAsString(anonymousObject);
            httpResponse.getWriter().append(json);
        }
    }


}
