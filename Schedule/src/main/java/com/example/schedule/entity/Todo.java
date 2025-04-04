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
@Table(name = "todo")
public class Todo {

    @Id // 기본 키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 값 자동 증가
    @Column(name = "id")
    private long id; // 할 일 고유 식별자

    @Column(name = "title", nullable = false) // title은 null이 될 수 없음
    private String title; // 할 일 제목

    @Column(name = "schedule", nullable = false) // schedule는 null이 될 수 없음
    private String schedule; // 할 일 내용

    @CreatedDate// 처음 생성될 때 자동으로 날짜 저장
    @Column(name = "writing", updatable = false) // 생성일자가 수정되지 않게
    private LocalDateTime writing; // 할 일이 생성된 날짜/시간

    @LastModifiedDate // 수정될 때 자동으로 날짜 갱신
    @Column(name = "creation")
    private LocalDateTime creation; // 할 일이 마지막으로 수정된 날짜/시간

    @Builder
    public Todo(Long userid, String title, String schedule, LocalDateTime writing, LocalDateTime creation){
        this.title = title;
        this.schedule = schedule;
        this.writing = LocalDateTime.now();
        this.creation = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    public void update(String title, String schedule){
        this.title = title;
        this.schedule = schedule;
    }

    public void updateTitle(String title){
        this.title = title;
    }
}
