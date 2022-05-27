package dev.babebbu.spring.core.models.entities.responses;

import dev.babebbu.spring.core.models.entities.Device;
import dev.babebbu.spring.core.utils.ObjectMapperFactory;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "elements", schema = "PUBLIC")
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Column()
    @Getter @Setter
    private String name;

    @Column()
    @Getter @Setter
    private String type;

    @Column(columnDefinition = "boolean default false")
    @Getter @Setter
    private Boolean publicDoor;

    @Column(columnDefinition = "boolean default false")
    @Getter @Setter
    private Boolean buildingDoor;

    @Column(columnDefinition = "boolean default false")
    @Getter @Setter
    private Boolean floorDoor;

    @Column()
    @Getter @Setter
    private String operationStatus;

    @Column()
    @Getter @Setter
    private String operationMode;

    @Column()
    @Getter @Setter
    private String connectivityStatus;

    @Column()
    @Getter @Setter
    private String latestConnectivityStatusMessage;

    @Column()
    @Getter @Setter
    private String latestConnectivityStatusTrace;

    @Column()
    @Getter @Setter
    private String openTime;

    @Column()
    @Getter @Setter
    private String closeTime;

    @Column(name = "position_x")
    @Getter @Setter
    private String positionX;

    @Column(name = "position_y")
    @Getter @Setter
    private String positionY;

    @Column()
    private String device;

    @Column()
    @Getter @Setter
    private Timestamp createdAt;

    @Column()
    @Getter @Setter
    private Timestamp updatedAt;

    @Column()
    @Getter @Setter
    private Timestamp deletedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "floor_plan_id", nullable = false)
    @JsonManagedReference
    @Getter @Setter
    private FloorPlan floorPlan;

    public void setDevice(Device device) throws JsonProcessingException {
        this.device = ObjectMapperFactory.getObjectMapper().writeValueAsString(device);
    }

    public Device getDevice() throws JsonProcessingException {
        return ObjectMapperFactory.getObjectMapper().readValue(device, Device.class);
    }
}
