package com.codesoom.assignment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Parse generic to json style string.
 */
public class JsonParser<T> {
    ObjectMapper objectMapper = new ObjectMapper();

    public String toJson(T object) throws IOException {
        OutputStream outputStream = new ByteArrayOutputStream();
        objectMapper.writeValue(outputStream, object);
        return outputStream.toString();
    }
}
