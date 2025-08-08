package com.oasb.literatura.API.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ConvierteDatos implements IConvierteDatos{

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {

        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir le formato JSON: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("ERROR  CON EL  ARCHIVO JSON, NO SE PUDO PROCESAR", e);
        }
    }
}
