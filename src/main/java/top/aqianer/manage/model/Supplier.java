package top.aqianer.manage.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "suppliers")
@Data
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(nullable = false)
    private String name;

    @Column(name = "contact_info", length = 255)
    private String contactInfo;

    @Column(length = 500) // 匹配address字段长度限制
    private String address;
}