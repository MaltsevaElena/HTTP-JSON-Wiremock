package ru.maltseva.elena.factory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFactoryImpl implements ObjectMapperFactory {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public ObjectMapperFactoryImpl() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
