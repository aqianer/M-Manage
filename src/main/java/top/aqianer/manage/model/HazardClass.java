package top.aqianer.manage.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "hazard_classes")
@Data
public class HazardClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long classId;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 20)
    private String symbol;
}