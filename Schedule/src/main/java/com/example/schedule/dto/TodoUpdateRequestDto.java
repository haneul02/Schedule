package com.example.schedule.dto;

import com.example.schedule.entity.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor // 기본 생성자 자동 생성
public class TodoUpdateRequestDto {

    private Long id;
    private String title;
    private String todo;
    private LocalDateTime update;

    @Builder
    public TodoUpdateRequestDto(Todo todo){
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.todo = todo.getTodo();
        this.update = todo.getUpdate();
    }

    public Todo toEntity(){
        return Todo.builder()
                .title(title)
                .todo(todo)
                .update(update)
                .build();
    }

}
