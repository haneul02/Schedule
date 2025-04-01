package com.example.schedule.dto;

import com.example.schedule.entity.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class TodoUpdateRequestDto {

    private final String title;

    private final String todo;


    @Builder
    public TodoUpdateRequestDto(Todo todo){
        this.title = todo.getTitle();
        this.todo = todo.getTodo();
    }

    public Todo toEntity(){
        return Todo.builder()
                .title(title)
                .todo(todo)
                .build();
    }

}
