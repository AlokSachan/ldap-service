package com.alok.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@Component
@Slf4j
public class MyCustomHeaderFilter extends OncePerRequestFilter {


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        log.info("2/3 " + MyCustomHeaderFilter.class.getSimpleName());
        HttpServletRequest wrappedRequest = new HttpServletRequestWrapper(request) {

            final String myCustomHeaderName = "Custom-Header";
            final String myCustomHeaderValue = "FooBar";

            @Override
            public Enumeration<String> getHeaderNames() {
                List<String> headerNames = Collections.list(super.getHeaderNames());
                headerNames.add(myCustomHeaderName);
                return Collections.enumeration(headerNames);
            }

            @Override
            public Enumeration<String> getHeaders(String name) {
                if (name.equals(myCustomHeaderName)) {
                    return Collections.enumeration(Collections.singletonList(myCustomHeaderValue));
                }
                return super.getHeaders(name);
            }

            @Override
            public String getHeader(String name) {
                if (name.equals(myCustomHeaderName)) {
                    return myCustomHeaderValue;
                }
                return super.getHeader(name);
            }
        };

        chain.doFilter(wrappedRequest, response);
    }
}
