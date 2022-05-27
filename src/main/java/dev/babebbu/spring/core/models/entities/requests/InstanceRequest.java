package dev.babebbu.spring.core.models.entities.requests;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "instances")
@Data
public class InstanceRequest implements Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "image_id")
    private Integer imageId;
}
