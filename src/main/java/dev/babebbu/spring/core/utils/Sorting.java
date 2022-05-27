package dev.babebbu.spring.core.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.beans.ConstructorProperties;

@Data
@AllArgsConstructor
public class Sorting {

    public final String key;
    public final boolean reverse;

    public Sorting(String s) {
        String[] splited = s.split("_");
        if (splited.length > 1) {
            this.key = splited[0];
            String mode = splited[1].toLowerCase();
            this.reverse = !"asc".equals(mode);
        }
        else {
            this.key = s;
            this.reverse = true;
        }
    }
}