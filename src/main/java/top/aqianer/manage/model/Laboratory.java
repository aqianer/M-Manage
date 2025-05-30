package top.aqianer.manage.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "laboratory")
@Data
public class Laboratory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lab_name", nullable = false, length = 100)
    private String labName;

    @Column(nullable = false, length = 100)
    private String school;

    @Column(name = "registrant_member_id")
    private Long registrant_member_id;

    @Lob
    private String description;

    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('active','inactive','archived')")
    private Status status = Status.active;

    public enum Status {
        active, inactive, archived
    }
}