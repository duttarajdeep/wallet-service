package com.wallet.wallet_service.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class RequestLoggingFilter implements Filter {
    private static final String HEADER_REQUEST_ID = "X-Request-ID";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String reqId = request.getHeader(HEADER_REQUEST_ID);
        if (reqId == null || reqId.isBlank())
            reqId = UUID.randomUUID().toString();

        response.setHeader(HEADER_REQUEST_ID, reqId);

        log.info("[{}] {} {}", reqId, request.getMethod(), request.getRequestURI());

        chain.doFilter(servletRequest, servletResponse);

        log.info("[{}] Completed with status {}", reqId, response.getStatus());
    }
}
