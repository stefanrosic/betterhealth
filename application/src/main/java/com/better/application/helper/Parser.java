package com.better.application.helper;

import com.better.application.exception.UnsupportedFileException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class Parser {

    private final XmlMapper xmlMapper;

    private final ObjectMapper objectMapper;

    public Parser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.xmlMapper = new XmlMapper();
    }

    public <D, T> D parse(final String resourcePath, Class<D> outClass) {
        final var isJson = resourcePath.contains(".json");
        final var isXml = resourcePath.contains(".xml");

        if (!isJson && !isXml) {
            throw new UnsupportedFileException();
        }

        try {
            final var payload = Files.readString(Paths.get(resourcePath));

            if (isJson) {
                return objectMapper.readValue(payload, outClass);
            }

            return xmlMapper.readValue(payload, outClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
