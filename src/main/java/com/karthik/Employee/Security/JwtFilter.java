package com.karthik.Employee.Security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Log4j2
public class JwtFilter extends OncePerRequestFilter {
	private final JwUtil jwUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
		log.info("----AUTHORIZING---------");
		String authHeader = request.getHeader("Authorization");
		if(!request.getRequestURI().equalsIgnoreCase("/login")){
			if(authHeader!=null && authHeader.startsWith("Bearer ")){
				String token = authHeader.substring(7);
				String sessionIdFromJwt=null;
				try{
					sessionIdFromJwt = (String)jwUtil.extractAllClaims(token).get("sessionId");
				}catch(Exception e){
					log.info("Exception while Authorizing {} ", e.getMessage());
				}


				HttpSession session = request.getSession(false);
				if(session!=null && session.getId().equals(sessionIdFromJwt)){
					filterChain.doFilter(request, response);
					return;
				}else{
					response.sendError(403,"Session expired or revoked");
				}
			}else{
				response.sendError(403,"Session expired or revoked");
				filterChain.doFilter(request,response);
			}
		}
		filterChain.doFilter(request,response);
	}
}
