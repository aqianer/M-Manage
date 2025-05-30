package top.aqianer.manage.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "lab_member")
@Data
public class LabMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lab_id", referencedColumnName = "id")
    private Laboratory laboratory;

    @Column(name = "member_name", nullable = false, length = 50)
    private String memberName;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('admin','member','guest')")
    private Permission permission = Permission.member;

    @Column(nullable = false, unique = true, length = 20)
    private String phone;

    @Column(nullable = false, length = 255, columnDefinition = "VARCHAR(255) NOT NULL COMMENT '邮箱'")
    private String email;

    @Column(nullable = false, length = 255, columnDefinition = "VARCHAR(255) NOT NULL COMMENT '登录密码'")
    private String password;

    @CreationTimestamp
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    public enum Permission {
        admin, member, guest
    }
}