package top.aqianer.manage.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "drugs")
@Data
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drug_id")
    private Long drugId;

    @NotBlank(message = "名称不能为空")
    private String name;

    @Pattern(regexp = "\\d{2,7}-\\d{2}-\\d", message = "CAS号格式不正确")
    private String casNumber;

    @Column(name = "english_name")
    private String englishName;

    @Column(length = 100, nullable = true)
    private String specification;

    @ManyToOne
    @JoinColumn(name = "manufacturer", referencedColumnName = "supplier_id")
    private Supplier manufacturer;

    @ManyToOne
    @JoinColumn(name = "storage_conditions", referencedColumnName = "storage_type_id")
    private StorageType storageConditions;

    @ManyToOne
    @JoinColumn(name = "hazard_class", referencedColumnName = "class_id")
    private HazardClass hazardClass;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}