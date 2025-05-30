package top.aqianer.manage.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    @ManyToOne
    @JoinColumn(name = "drug_id")
    private Drug drug;

    @ManyToOne
    @JoinColumn(name = "lab_id", referencedColumnName = "id")
    private Laboratory laboratory;

    @Column(name = "current_stock", columnDefinition = "INT DEFAULT 0")
    private Integer currentStock;

    @Column(name = "min_threshold", columnDefinition = "INT DEFAULT 10")
    private Integer minThreshold;

    @Column(name = "max_threshold", columnDefinition = "INT DEFAULT 1000")
    private Integer maxThreshold;
    
    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    // 新增location字段映射
    @Column(length = 100)
    private String location;

    // 添加时间戳字段映射
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "last_modified_by", referencedColumnName = "id")
    private LabMember lastModifiedBy;
}