package dev.babebbu.spring.core.models.entities;

import lombok.Data;

@Data
public class DeviceIdentifiers {

    private String ingress;
    private String egress;
    private String relay;

}
