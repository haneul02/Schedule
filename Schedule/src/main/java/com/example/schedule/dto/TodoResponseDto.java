package com.example.schedule.dto;

import com.example.schedule.entity.Todo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoResponseDto {

    private final Long id;

    private final String title;

    private final String schedule;

    private LocalDateTime writing;

    @Builder
    public TodoResponseDto (Todo todo){
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.schedule = todo.getSchedule();
        this.writing = todo.getWriting();
    }

}
