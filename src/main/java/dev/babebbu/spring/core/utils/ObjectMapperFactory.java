package dev.babebbu.spring.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ObjectMapperFactory {

    private static final ObjectMapper mapper = new ObjectMapper()
        .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public static ObjectMapper getObjectMapper() {
        return mapper;
    }

}
