package dev.babebbu.spring.core.models.entities;

import dev.babebbu.spring.core.utils.ObjectMapperFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.Setter;

public class Device {

    @Getter
    @Setter
    private String profile;
    private String identifiers;

    public void setIdentifiers(DeviceIdentifiers identifiers) throws JsonProcessingException {
        this.identifiers = ObjectMapperFactory.getObjectMapper().writeValueAsString(identifiers);
    }

    public DeviceIdentifiers getIdentifiers() throws JsonProcessingException {
        return ObjectMapperFactory.getObjectMapper().readValue(identifiers, DeviceIdentifiers.class);
    }
}
