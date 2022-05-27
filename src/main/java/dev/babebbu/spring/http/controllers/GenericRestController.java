package dev.babebbu.spring.http.controllers;

import dev.babebbu.spring.core.utils.SearchCriteria;
import dev.babebbu.spring.core.utils.Sorting;
import dev.babebbu.spring.core.services.implementations.ResourceService;
import dev.babebbu.spring.http.providers.RequestEntitiesProvider;
import dev.babebbu.spring.http.providers.ResponseEntitiesProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class GenericRestController {

    private final ResourceService resourceService;
    private final RequestEntitiesProvider requestEntitiesProvider;
    private final ResponseEntitiesProvider responseEntitiesProvider;
    private final ObjectMapper objectMapper;

    @PersistenceContext
    private EntityManager entityManager;

    private final Map<String, List<String>> searchable = Map.of(
        "instances", List.of("name"),
        "buildings", List.of("id", "name"),
        "floor-plans", List.of("id", "name"),
        "elements", List.of("id", "name")
    );

    @GetMapping("{resource}")
    public Object list(
        @PathVariable String resource,
        @RequestParam(name = "search", required = false) String searchString,
        @RequestParam(name = "order", required = false) String sortString,
        Pageable pageable
    ) {
        return resourceService.list(
            responseEntitiesProvider.resolve(resource),
            pageable,
            searchString,
            searchable.get(resource).stream().map(SearchCriteria::new).collect(Collectors.toList()),
            Optional.ofNullable(sortString).map(s -> {
                List<Sorting> sorting = Arrays.stream(s.split(","))
                    .map(Sorting::new)
                    .collect(Collectors.toList());
                //Collections.reverse(sorting);
                return sorting;
            })
            .orElse(List.of(new Sorting("id")))
        );
    }

    @GetMapping("{resource}/{id}")
    public Object get(
        @PathVariable(name = "resource") String resource,
        @PathVariable(name = "id") int id
    ) {
        return resourceService.get(responseEntitiesProvider.resolve(resource), id);
    }

    @GetMapping("/{resource}/{id}/{relationship}")
    public Object getBelongingFloorPlans(
        @PathVariable("id") Integer id,
        @PathVariable("relationship") String relationship,
        @RequestParam(name = "order", required = false) String sortString,
        @RequestParam(name = "search", required = false) String searchString,
        Pageable pageable
    ) {
        return resourceService.list(
            responseEntitiesProvider.resolve(relationship),
            pageable,
            searchString,
            Stream.of("name").map(SearchCriteria::new).collect(Collectors.toList()),
            Optional.ofNullable(sortString).map(s -> {
                List<Sorting> sorting = Arrays.stream(s.split(","))
                    .map(Sorting::new)
                    .collect(Collectors.toList());
                //Collections.reverse(sorting);
                return sorting;
            }).orElse(List.of(new Sorting("id")))
        );
    }

    @PostMapping("{resource}")
    public Object create(@PathVariable(name = "resource") String resource, @RequestBody Object body) {
        return resourceService.save(
            objectMapper.convertValue(body, requestEntitiesProvider.resolve(resource)),
            responseEntitiesProvider.resolve(resource)
        );
    }

    @DeleteMapping("{resource}/{id}")
    public Object delete(
        @PathVariable(name = "resource") String resource,
        @PathVariable(name = "id") int id
    ) {
        return resourceService.delete(responseEntitiesProvider.resolve(resource), id);
    }

}
