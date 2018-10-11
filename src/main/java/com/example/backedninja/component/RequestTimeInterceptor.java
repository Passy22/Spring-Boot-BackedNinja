package com.example.backedninja.component;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.backedninja.repository.LogRepository;

@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	@Qualifier("logRepository")
	private LogRepository logRepository;
	
	// Vamos a comparar tiempos para ver cuanto tiempo tarda en ejecutar un metodo con las clases
	// preHandle y afterCompletion.
	private Log Logger = LogFactory.getLog(RequestTimeInterceptor.class);

	// Se ejecuta en primer lugar
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Cogemos la request y calculamos la hora actual
		request.setAttribute("startTime", System.currentTimeMillis());
		
		return super.preHandle(request, response, handler);
	}

	// Se ejecuta en segundo lugar
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// Volvemos a recoger desde la request el tiempo actual, y ahora calculamos el tiempo total
		// En primer lugar le pasamos el valor del tiempo anterior recogiendo el atributo y 
		// casteamos a long
		long startTime = (long) request.getAttribute("startTime");
		String url = request.getRequestURL().toString();
		
		
		// Autentificación mediante Spring Security
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = "";
		if( auth != null && auth.isAuthenticated()) {
			username = auth.getName();
		}
		logRepository.save(new com.example.backedninja.entity.Log(new Date(), auth.getDetails().toString(), username, url));
		
		
		
		Logger.info("Url to: '" +  url
				+ "' in: '" 
				+ (System.currentTimeMillis() - startTime) 
				+ "'ms");
		super.afterCompletion(request, response, handler, ex);
	}
	
	
	
	// Por cada peticion que nos hagan entrara en esta clase. De ahí su nombre
	// interceptor
	
	

}
