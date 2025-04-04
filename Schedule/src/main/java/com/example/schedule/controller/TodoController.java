package com.example.schedule.controller;

import com.example.schedule.dto.TodoRequestDto;
import com.example.schedule.dto.TodoResponseDto;
import com.example.schedule.dto.TodoUpdateRequestDto;
import com.example.schedule.dto.TodoUpdateResponseDto;
import com.example.schedule.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    // 일정 등록
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSchedule(@RequestBody TodoRequestDto requestDto) {
        System.out.println("title = " + requestDto.getTitle());
        System.out.println("schedule = " + requestDto.getSchedule());

        todoService.createSchedule(requestDto);
    }

    // 전체일정 조회
    @GetMapping
    public List<TodoResponseDto> getAllTodo() {
        return todoService.getAllTodo();
    }

    // 특정 일정 조회
    @GetMapping("/{id}")
    public TodoResponseDto getTodoMyId(@PathVariable Long id){
        return todoService.getTodoMyId(id);
    }

    // 일정 전체 수정
    @PutMapping("/{id}")
    public TodoUpdateResponseDto updateTodo(@PathVariable Long id, @RequestBody TodoUpdateRequestDto requestDto){
        return todoService.updateTodo(
                id, requestDto.getTitle(), requestDto.getSchedule()
        );
    }

    // 일정 제목만 수정
    @PutMapping("/{id}/title")
    public TodoUpdateResponseDto updateTitle(@PathVariable Long id, @RequestBody TodoUpdateRequestDto requestDto) {
        System.out.println("title = " + requestDto.getTitle());

        return todoService.updateTitle(
                id,
                requestDto.getTitle()
        );
    }

    // 일정 삭제
    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}
