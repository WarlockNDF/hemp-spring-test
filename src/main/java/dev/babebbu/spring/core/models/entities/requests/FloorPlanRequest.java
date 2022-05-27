package dev.babebbu.spring.core.models.entities.requests;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "floor_plans")
@Data
public class FloorPlanRequest implements Request{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer floor;

    @Column(name = "building_id")
    private Integer buildingId;

    @Column(name = "image")
    private String image;

}
