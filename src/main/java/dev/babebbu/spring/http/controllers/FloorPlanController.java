package dev.babebbu.spring.http.controllers;

import dev.babebbu.spring.core.models.entities.requests.FloorPlanRequest;
import dev.babebbu.spring.core.models.entities.responses.FloorPlan;
import dev.babebbu.spring.core.services.implementations.ResourceService;
import dev.babebbu.spring.core.services.implementations.StorageService;
import dev.babebbu.spring.core.utils.DataUrl;
import dev.babebbu.spring.http.providers.RequestEntitiesProvider;
import dev.babebbu.spring.http.providers.ResponseEntitiesProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class FloorPlanController {

    private final ResourceService resourceService;
    private final RequestEntitiesProvider requestEntitiesProvider;
    private final ResponseEntitiesProvider responseEntitiesProvider;
    private final ObjectMapper objectMapper;
    private final StorageService storageService;

    /**
     * Overriding default controller in case there is anything in addition to CRUD such as
     * saving file to storage before persist on database.
     * @param request FloorPlanRequest
     * @return Object
     */
    @SneakyThrows
    @PostMapping("floor-plans")
    public Object create(@RequestBody FloorPlanRequest request) {
        // Extract Base64 Data URL.
        DataUrl dataUrl = DataUrl.fromUrl(request.getImage());

        // Exit if image is malformed.
        if (dataUrl.isEncodingNotEquals("base64")) {
            return ResponseEntity.badRequest().body(Map.of(
                "message", "Malformed image request."
            ));
        }

        // Save image.
        byte[] imageBytes = Base64.getDecoder().decode(dataUrl.getData());
        String filename = storageService.local().save(request.getImage().getBytes(StandardCharsets.UTF_8));
        request.setImage(filename);

        // Insert a record.
        return resourceService.save(request, FloorPlan.class);
    }

}
