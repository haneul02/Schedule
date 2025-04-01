package com.example.schedule.dto;

import com.example.schedule.entity.Todo;
import lombok.Getter;

@Getter
public class TodoRequestDto {

    private final String title;

    private final String todo;

    public TodoRequestDto(String title, String todo){
        this.title = title;
        this.todo = todo;
    }

    public Todo toEntity(){
        return Todo.builder()
                .title(title)
                .todo(todo)
                .build();
    }
}

