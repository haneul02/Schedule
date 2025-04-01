package com.example.schedule.dto;

import com.example.schedule.entity.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor // 기본 생성자 자동 생성
public class TodoRequestDto {

    private String title;             // 제목
    private String todo;              // 내용
    private LocalDateTime creation;   // 저장 시간

    @Builder
    public TodoRequestDto (Todo todo){
        this.title = todo.getTitle();
        this.todo = todo.getTodo();
        this.creation = todo.getCreation();
    }

    public Todo toEntity(){
        return Todo.builder()
                .title(title)
                .todo(todo)
                .creation(creation)
                .build();
    }
}

