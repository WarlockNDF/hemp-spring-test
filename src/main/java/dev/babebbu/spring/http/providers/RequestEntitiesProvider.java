package dev.babebbu.spring.http.providers;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RequestEntitiesProvider implements EntitiesProvider {

    private final Map<String, Class<?>> entities = Map.of(
        "floor-plans", FloorPlanRequest.class
    );

    public Class<?> resolve(String s) {
        return entities.get(s);
    }

}
