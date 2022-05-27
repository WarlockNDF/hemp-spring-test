package dev.babebbu.spring.core.models.entities.responses;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "images")
@Data
public class VirtualMachineImage {

    @Id
    private Integer id;

    private String name;

    private String distroLogo;
}
