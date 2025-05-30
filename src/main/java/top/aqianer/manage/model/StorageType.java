package top.aqianer.manage.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "storage_types")
@Data
public class StorageType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storage_type_id")
    private Long storageTypeId;

    @Column(nullable = false, length = 100)
    private String description;
}