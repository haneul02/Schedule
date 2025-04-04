package com.example.schedule.dto;

import com.example.schedule.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@AllArgsConstructor
@Builder
public class TodoUpdateRequestDto {

    private final String title;

    private final String schedule;

    public TodoUpdateRequestDto(Todo todo){
        this.title = todo.getTitle();
        this.schedule = todo.getSchedule();
    }

    public Todo toEntity(){
        return Todo.builder()
                .title(title)
                .schedule(schedule)
                .build();
    }

}
