package com.example.schedule.dto;

import com.example.schedule.entity.Todo;
import lombok.Getter;

@Getter
public class TodoRequestDto {

    private final String title;

    private final String schedule;

    public TodoRequestDto(String title, String schedule){
        this.title = title;
        this.schedule = schedule;
    }

    public Todo toEntity(){
        return Todo.builder()
                .title(title)
                .schedule(schedule)
                .build();
    }
}

