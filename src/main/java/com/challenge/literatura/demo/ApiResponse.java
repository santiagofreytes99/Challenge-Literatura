package com.challenge.literatura.demo;

import java.util.List;

public class ApiResponse {
    private List<LibrosRecord> results;

    // Getters y Setters
    public List<LibrosRecord> getResults() {
        return results;
    }

    public void setResults(List<LibrosRecord> results) {
        this.results = results;
    }
}