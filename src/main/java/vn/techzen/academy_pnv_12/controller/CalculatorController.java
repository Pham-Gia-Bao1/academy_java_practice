package vn.techzen.academy_pnv_12.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CalculatorController {

    private static final String FIRST_NUMBER_ERROR = "First number cannot be empty";
    private static final String SECOND_NUMBER_ERROR = "Second number cannot be empty";
    private static final String OPERATOR_ERROR = "Operator cannot be empty";
    private static final String INVALID_NUMBER_ERROR = "Number is not a valid number";
    private static final String ZERO_DIVISION_ERROR = "Second number cannot be zero";
    private static final String INVALID_OPERATOR_ERROR = "Invalid operator";

    @PostMapping("/api/calculator")
    public ResponseEntity<Map<String, Object>> calculate(
            @RequestParam(value = "firstNumber", defaultValue = "") String firstNumber,
            @RequestParam(value = "secondNumber", defaultValue = "") String secondNumber,
            @RequestParam(value = "operator", defaultValue = "") String operator
    ) {
        Map<String, Object> response = new HashMap<>();

        if (isEmpty(firstNumber)) {
            response.put("error", FIRST_NUMBER_ERROR);
            return ResponseEntity.badRequest().body(response);
        }

        if (isEmpty(secondNumber)) {
            response.put("error", SECOND_NUMBER_ERROR);
            return ResponseEntity.badRequest().body(response);
        }

        if (isEmpty(operator)) {
            response.put("error", OPERATOR_ERROR);
            return ResponseEntity.badRequest().body(response);
        }

        if (!isNumeric(firstNumber)) {
            response.put("error", INVALID_NUMBER_ERROR);
            return ResponseEntity.badRequest().body(response);
        }

        if (!isNumeric(secondNumber)) {
            response.put("error", INVALID_NUMBER_ERROR);
            return ResponseEntity.badRequest().body(response);
        }

        double firstNumberParsed = Double.parseDouble(firstNumber);
        double secondNumberParsed = Double.parseDouble(secondNumber);

        try {
            double result = calculateResult(firstNumberParsed, secondNumberParsed, operator);
            response.put("result", result);
            return ResponseEntity.ok().body(response);
        } catch (ArithmeticException | IllegalArgumentException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    private double calculateResult(double firstNumber, double secondNumber, String operator) {
        switch (operator) {
            case "+" -> {
                return firstNumber + secondNumber;
            }
            case "-" -> {
                return firstNumber - secondNumber;
            }
            case "*" -> {
                return firstNumber * secondNumber;
            }
            case "/" -> {
                if (secondNumber == 0) {
                    throw new ArithmeticException(ZERO_DIVISION_ERROR);
                }
                return firstNumber / secondNumber;
            }
            default -> {
                throw new IllegalArgumentException(INVALID_OPERATOR_ERROR);
            }
        }
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
