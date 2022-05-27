package dev.babebbu.spring.core.utils;

import lombok.Data;

@Data
public class SearchCriteria {
    public String key;
    public String operand;
    public String value;

    public SearchCriteria(String key) {
        this.key = key;
        this.operand = "=";
    }
}
