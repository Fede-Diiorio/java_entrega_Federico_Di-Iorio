package com.coderhouse.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.coderhouse.dtos.TimeResponseDTO;

@Service
public class DateService {

    @Autowired
    private RestTemplate restTemplate;

    public LocalDateTime getCurrentDateTime() {
        try {
            final String URL = "https://timeapi.io/api/Time/current/zone?timeZone=America/Argentina/Buenos_Aires";
            TimeResponseDTO response = restTemplate.getForObject(URL, TimeResponseDTO.class);

            if (response != null) {
                String dateTimeString = String.format("%d-%02d-%02dT%02d:%02d:%02d.%03d",
                    response.getYear(),
                    response.getMonth(),
                    response.getDay(),
                    Integer.parseInt(response.getTime().split(":")[0]), 
                    Integer.parseInt(response.getTime().split(":")[1]), 
                    response.getSeconds(), 
                    response.getMilliSeconds()); 

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

                return LocalDateTime.parse(dateTimeString, formatter);
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

