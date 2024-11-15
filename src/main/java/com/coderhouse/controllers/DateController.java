package com.coderhouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.dto.TimeResponseDTO;
import com.coderhouse.services.DateService;

@RestController
@RequestMapping("/api/date")
public class DateController {

	@Autowired
	private DateService dateService;
	
	private int dateCounter = 0;
	private String lastDate = "N/A";
	
	@GetMapping
	public ResponseEntity<String> getDate() {
		dateCounter++;
		TimeResponseDTO date = dateService.getDate();
		
		if(date == null) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body("Error al obtener la fecha desde al API.");
		}
		
		String dateMessage = String.format(
				"Fecha Actual: %s %s %d, %d\nHora: %s\nNúmero de invocaciones: %d\nÚltima fecha mostrada: %s",
				date.getDayOfWeek(), 
				date.getMonth(),
				date.getDay(), 
				date.getYear(),
				date.getTime(), 
				dateCounter, 
				lastDate);
		
		lastDate = String.format("%s %s %d, %d %s",
				date.getDayOfWeek(), date.getMonth(), date.getDay(), date.getYear(), date.getTime());
		
		return ResponseEntity.ok(dateMessage);
	}
}
