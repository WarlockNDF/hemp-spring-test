package dev.babebbu.spring.core.models.entities.requests;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
public class VirtualMachineImageRequest implements Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String distroLogo;
}
