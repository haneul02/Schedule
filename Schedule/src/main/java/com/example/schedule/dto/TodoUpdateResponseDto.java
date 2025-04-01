package com.example.schedule.dto;

import com.example.schedule.entity.Todo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoUpdateResponseDto {

    private final Long id;

    private final String title;

    private final String todo;

    private LocalDateTime update;

    @Builder
    public TodoUpdateResponseDto (Todo todo){
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.todo = todo.getTodo();
        this.update = todo.getUpdate();
    }
}
