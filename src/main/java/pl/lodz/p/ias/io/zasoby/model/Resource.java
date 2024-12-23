package pl.lodz.p.ias.io.zasoby.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.lodz.p.ias.io.zasoby.utils.ResourceStatus;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String resourceName;
    private String resourceType;
    private int resourceQuantity;
    private ResourceStatus resourceStatus;
    private long warehouseId;
    private String volunteerName;
    private String assignedTask;

    public Resource(String resourceName, String resourceType, int resourceQuantity, long warehouseId) {
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.resourceQuantity = resourceQuantity;
        this.warehouseId = warehouseId;
        resourceStatus = ResourceStatus.NIEPRZYDZIELONY;
    }
}
