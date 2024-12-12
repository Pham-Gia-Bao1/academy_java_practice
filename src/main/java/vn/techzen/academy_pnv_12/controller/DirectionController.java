package vn.techzen.academy_pnv_12.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class DirectionController {

    private  final Map<String, String> dictionsMap = Map.ofEntries(
            Map.entry("hello", "xin chào"),
            Map.entry("apple", "táo"),
            Map.entry("rice", "cơm"),
            Map.entry("good morning", "chào buổi sáng"),
            Map.entry("orange", "cam")
    );

    @GetMapping("/api/dictionary")
    public ResponseEntity<Map<String, String>> dictionary(@RequestParam(defaultValue = "") String word) {
        String translation = dictionsMap.get(word.trim().toLowerCase());

        if (translation == null) {
            return ResponseEntity.notFound().build();
        }

        // Create a response map to return
        Map<String, String> response = new HashMap<>();
        response.put("translation", translation);

        return ResponseEntity.ok(response);
    }

}
