package dev.babebbu.spring.http.providers;

import cloud.compute.core.models.entities.responses.*;
import dev.babebbu.spring.core.models.entities.responses.Building;
import dev.babebbu.spring.core.models.entities.responses.Element;
import dev.babebbu.spring.core.models.entities.responses.FloorPlan;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ResponseEntitiesProvider implements EntitiesProvider {

    private final Map<String, Class<?>> entities = Map.of(
        "buildings", Building.class,
        "floor-plans", FloorPlan.class,
        "elements", Element.class
    );

    public Class<?> resolve(String s) {
        Class<?> entity = entities.get(s);
        if (entity == null) {
            throw new RuntimeException("Entity " + s + " has not been registered.");
        }
        return entities.get(s);
    }


}
