package com.karthik.Employee.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

public class MyInterceptor implements HandlerInterceptor {
	private static final Logger log = LoggerFactory.getLogger(MyInterceptor.class);
	private static final String TRACE_ID_KEY="traceId";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler){
		String traceId = request.getHeader("X-Trace-ID");
		if(traceId==null){
			traceId = UUID.randomUUID().toString();
		}
		MDC.put(TRACE_ID_KEY,traceId);
		MDC.put("name","Karthik");
		request.setAttribute("startTime",System.currentTimeMillis());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex){
		long startTime = (long)request.getAttribute("startTime");
		long totalTime = System.currentTimeMillis()-startTime;
		String sessionId = (request.getSession(false)!=null)?request.getSession().getId():"NO_SESSION";
		String traceId = MDC.get(TRACE_ID_KEY);
		log.info("TRACE:{} SESSION:{} TOTAL_TIME={},URL={},METHOD={},STATUS={}",
				traceId,sessionId,totalTime,request.getRequestURI(),request.getMethod(),response.getStatus());
		MDC.clear();
	}
}
