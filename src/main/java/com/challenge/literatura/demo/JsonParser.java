package com.challenge.literatura.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

public class JsonParser {
    public List<LibrosRecord> parsearLibros(String jsonResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ApiResponse apiResponse = objectMapper.readValue(jsonResponse, ApiResponse.class);
        return apiResponse.getResults();
    }
}