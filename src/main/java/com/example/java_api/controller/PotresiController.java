package com.example.java_api.controller;

import com.example.java_api.repo.PotresiRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PotresiController {
    int i=1;

@GetMapping("/test")

    public String greeting() {
        return String.format(PotresiRepo.najdiZadnjiMesec()
        );



    }
}
