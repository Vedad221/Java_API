package com.example.java_api.service;

import com.example.java_api.model.Potres;
import com.example.java_api.repo.PotresiRepo;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PotresiService {

    @Autowired
    private PotresiRepo potresiRepo;



}
