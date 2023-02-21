package com.example.demo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CafeUtils {

    private CafeUtils() {

    }

    public static ResponseEntity<String> getResponesEntity(String responseMassage, HttpStatus httpStatus) {
        return new ResponseEntity<>("{\"massage\":\"" + responseMassage + "\"}", httpStatus);
    }
}

