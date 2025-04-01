package com.example.schedule.dto;

import com.example.schedule.entity.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class TodoResponseDto {

    private final Long id;

    private final String title;

    private final String todo;

    private LocalDateTime creation;

    @Builder
    public TodoResponseDto (Todo todo){
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.todo = todo.getTodo();
        this.creation = todo.getCreation();
    }

}
