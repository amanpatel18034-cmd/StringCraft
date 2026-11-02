package com.stringcraft.stringcraft.controller;

import com.stringcraft.stringcraft.dto.StringRequest;
import com.stringcraft.stringcraft.dto.StringResponse;
import com.stringcraft.stringcraft.service.StringService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/string")
@CrossOrigin(origins = "*")
public class StringController {

    private final StringService stringService;

    public StringController(StringService stringService) {
        this.stringService = stringService;
    }

    @GetMapping("/welcome")
    public ResponseEntity<StringResponse> welcome() {
        return ResponseEntity.ok(StringResponse.ok("WELCOME", "Welcome to StringCraft - String Manipulation Toolkit!"));
    }

    @PostMapping("/reverse")
    public ResponseEntity<StringResponse> reverse(@RequestBody StringRequest request) {
        String input = request != null ? request.getText() : "";
        String result = stringService.reverse(input);
        return ResponseEntity.ok(StringResponse.ok("REVERSE", result));
    }

    @GetMapping("/reverse")
    public ResponseEntity<StringResponse> reverseGet(@RequestParam(defaultValue = "") String text) {
        String result = stringService.reverse(text);
        return ResponseEntity.ok(StringResponse.ok("REVERSE", result));
    }

    @PostMapping("/count-vowels")
    public ResponseEntity<StringResponse> countVowels(@RequestBody StringRequest request) {
        String input = request != null ? request.getText() : "";
        long count = stringService.countVowels(input);
        return ResponseEntity.ok(StringResponse.ok("COUNT_VOWELS", count));
    }

    @PostMapping("/count-consonants")
    public ResponseEntity<StringResponse> countConsonants(@RequestBody StringRequest request) {
        String input = request != null ? request.getText() : "";
        long count = stringService.countConsonants(input);
        return ResponseEntity.ok(StringResponse.ok("COUNT_CONSONANTS", count));
    }

    @PostMapping("/count-digits")
    public ResponseEntity<StringResponse> countDigits(@RequestBody StringRequest request) {
        String input = request != null ? request.getText() : "";
        long count = stringService.countDigits(input);
        return ResponseEntity.ok(StringResponse.ok("COUNT_DIGITS", count));
    }

    @PostMapping("/count-spaces")
    public ResponseEntity<StringResponse> countSpaces(@RequestBody StringRequest request) {
        String input = request != null ? request.getText() : "";
        long count = stringService.countSpaces(input);
        return ResponseEntity.ok(StringResponse.ok("COUNT_SPACES", count));
    }

    @PostMapping("/remove-spaces")
    public ResponseEntity<StringResponse> removeSpaces(@RequestBody StringRequest request) {
        String input = request != null ? request.getText() : "";
        String result = stringService.removeSpaces(input);
        return ResponseEntity.ok(StringResponse.ok("REMOVE_SPACES", result));
    }

    @PostMapping("/convert-case")
    public ResponseEntity<StringResponse> convertCase(@RequestBody StringRequest request) {
        String input = request != null ? request.getText() : "";
        String mode = request != null ? request.getMode() : "UPPER";
        String result = stringService.convertCase(input, mode);
        return ResponseEntity.ok(StringResponse.ok("CONVERT_CASE", result));
    }
}

