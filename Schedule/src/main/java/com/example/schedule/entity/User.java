package com.example.schedule.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor // JPA에서 엔티티 생성시 기본 생성자 자동 생성 해야해
@EntityListeners(AuditingEntityListener.class) // JPA Auditing 기능 활성화
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @CreatedDate// 처음 생성될 때 자동으로 날짜 저장
    @Column(name = "writing", updatable = false) // 생성일자가 수정되지 않게
    private LocalDateTime writing; // 할 일이 생성된 날짜/시간

    @LastModifiedDate // 수정될 때 자동으로 날짜 갱신
    @Column(name = "creation")
    private LocalDateTime creation; // 할 일이 마지막으로 수정된 날짜/시간

    @Builder
    public User(Long id, String name, String email, String password, LocalDateTime writing, LocalDateTime creation){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.writing = LocalDateTime.now();
        this.creation = LocalDateTime.now();
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
