package dev.babebbu.spring.cache;

import dev.babebbu.spring.core.utils.ObjectMapperFactory;

import java.util.HashMap;
import java.util.Map;

public class KeyValueInMemoryCache {

    private static final Map<String, Object> repository = new HashMap<>();

    public static <T> T get(String key, Class<T> typeRef) {
        return ObjectMapperFactory.getObjectMapper().convertValue(repository.get(key), typeRef);
    }

    public static void set(String key, Object value) {
        repository.put(key, value);
    }

    public static void clear() {
        repository.clear();
    }

    public static Map<String, Object> getRepository() {
        return repository;
    }

}
