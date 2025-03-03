package net.unir.mslibrarysearcher.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DefaultController {
    
    @GetMapping("/")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("MS Library Searcher");
    }
    
}
