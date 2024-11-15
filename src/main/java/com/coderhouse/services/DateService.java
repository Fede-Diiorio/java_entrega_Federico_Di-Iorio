package com.coderhouse.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.coderhouse.dto.TimeResponseDTO;

@Service
public class DateService {

    @Autowired
    private RestTemplate restTemplate;

    public TimeResponseDTO getDate() {
        try {
            final String URL = "https://timeapi.io/api/Time/current/zone?timeZone=America/Argentina/Buenos_Aires";
            return restTemplate.getForObject(URL, TimeResponseDTO.class);
        } catch (RestClientException e) {
            System.err.println("Error, no se pudo obtener la fecha desde la API externa: " + e.getMessage());
            return null;
        }
    }

    public LocalDateTime getCurrentDateTime() {
        try {
            final String URL = "https://timeapi.io/api/Time/current/zone?timeZone=America/Argentina/Buenos_Aires";
            TimeResponseDTO response = restTemplate.getForObject(URL, TimeResponseDTO.class);

            if (response != null) {
                String dateTimeString = String.format("%d-%02d-%02dT%s", 
                    response.getYear(), 
                    response.getMonth(), 
                    response.getDay(), 
                    response.getTime(),
                    response.getSeconds(),
                    response.getMiliSeconds());
                	

                return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            } else {
                throw new IllegalStateException("Respuesta nula desde la API externa.");
            }
        } catch (RestClientException e) {
            System.err.println("Error al obtener la fecha desde la API externa: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error al procesar la respuesta de la API externa: " + e.getMessage());
            e.printStackTrace();
        }

        return LocalDateTime.now();
    }
}

