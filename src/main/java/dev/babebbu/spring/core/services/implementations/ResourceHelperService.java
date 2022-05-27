package dev.babebbu.spring.core.services.implementations;

import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class ResourceHelperService {

    public Predicate findAllById(CriteriaBuilder cb, Root<?> root, String search) {
        return cb.like(root.get("id").as(String.class), "%" + search + "%");
    }

}
