package com.interceptor.horario.acceso.controllers;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AppController {
	
	@GetMapping("/show")
	public ResponseEntity<?> show() {
		Map<String, Object> data = new HashMap<>();
		data.put("title", "Bienvenidos al sistema de atencion!");
		data.put("time", new Date());
		return ResponseEntity.ok(data);
	}
}
