package dev.babebbu.spring.core.models.entities.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "instances")
@Data
public class Instance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @JsonIgnore
    private String password;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "image_id")
    private VirtualMachineImage virtualMachineImage;

    public String getImageName() {
        if (virtualMachineImage != null) {
            return virtualMachineImage.getId() + " " + virtualMachineImage.getName();
        }
        return null;
    }
}
