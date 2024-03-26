package com.interceptor.horario.acceso.interceptors;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CalendarInterceptor implements HandlerInterceptor{

	@Value("{config.calendar.open}")
	private Integer openHour;
	
	@Value("{config.calendar.close}")
	private Integer CloseHour;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		System.out.println(hour);
		
		if(hour >= openHour && hour < CloseHour) {
			StringBuilder message = new StringBuilder("Bienvenido al horario de atenciòn al cliente");
			message.append(", atendemos desde las ");
			message.append(openHour);
			message.append("hrs.");
			message.append(" hasta las ");
			message.append(CloseHour);
			message.append("hrs.");
			message.append(" Gracias por su visita!");
			request.setAttribute("message", message.toString());			
			return true;
		}
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> data = new HashMap<>();
		StringBuilder message = new StringBuilder("Cerrado, fuera del horario de atencion");
		message.append("por favor visitenos desde las");
		message.append(openHour);
		message.append("y las ");
		message.append(CloseHour);
		message.append("hrs. Gracias");
		data.put("message", message.toString());
		data.put("date", new Date());
		/*Convirtiendo en JSON y pasando ala respuesta*/
		response.setContentType("application/json");
		response.setStatus(401);
		response.getWriter().write(mapper.writeValueAsString(message));
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

}
