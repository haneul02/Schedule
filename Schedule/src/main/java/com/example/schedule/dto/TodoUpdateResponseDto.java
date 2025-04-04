package com.example.schedule.dto;

import com.example.schedule.entity.Todo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoUpdateResponseDto {

    private final Long id;

    private final String title;

    private final String schedule;

    private LocalDateTime creation;

    @Builder
    public TodoUpdateResponseDto (Todo todo){
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.schedule = todo.getSchedule();
        this.creation = todo.getCreation();
    }
}
