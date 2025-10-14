package cse.skku.edu.dailycs.entity;

import cse.skku.edu.dailycs.util.enumType.UserStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String provider; // google, naver

    @Column(nullable = false, unique = true, length = 320)
    private String email;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(length = 500)
    private String imageUrl;

    @Column(nullable = false, length = 20)
    private UserStatus status = UserStatus.ACTIVE; // ACTIVE, BLOCKED, DELETED

    @Column(nullable = false)
    private LocalDateTime lastLoginAt;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column
    private LocalDateTime deletedAt;
}
