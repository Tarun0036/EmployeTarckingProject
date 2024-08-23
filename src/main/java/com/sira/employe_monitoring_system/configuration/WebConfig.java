package com.sira.employe_monitoring_system.configuration;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class WebConfig implements Filter{
	 @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	        // Initialization logic if needed
	    }

	    @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {
	        
	        HttpServletResponse res = (HttpServletResponse) response;
	        HttpServletRequest req = (HttpServletRequest) request;

	        res.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
	        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	        res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
	        res.setHeader("Access-Control-Allow-Credentials", "true");

	        // Handle the preflight request (OPTIONS)
	        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
	            res.setStatus(HttpServletResponse.SC_OK);
	        } else {
	            chain.doFilter(request, response);
	        }
	    }

	    @Override
	    public void destroy() {
	        // Cleanup logic if needed
	    }
}
