package com.example.schedule.service;

import com.example.schedule.dto.TodoRequestDto;
import com.example.schedule.dto.TodoResponseDto;
import com.example.schedule.entity.Todo;
import com.example.schedule.exception.IdException;
import com.example.schedule.repository.TodoReqository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoReqository todoReqository;

    // 일정 저장
    @Transactional
    public void createSchedule(TodoRequestDto requestDto){
        if(requestDto.getTitle() == null || requestDto.getTodo() == null ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "모든 항목을 작성해주세요.");
        }
        Todo todo = requestDto.toEntity();
        todoReqository.save(todo);
    }

    // 전체 일정 조회
    @Transactional(readOnly = true)
    public List<TodoResponseDto> getAllTodo(){
        return todoReqository.findAll().stream()
                .map(TodoResponseDto::new)
                .toList();
    }

    // 특정 일정 조회
    @Transactional(readOnly = true)
    public TodoResponseDto getTodoMyId(Long id){
        Todo todo = todoReqository.findById(id)
                .orElseThrow(IdException::new);
        return new TodoResponseDto(todo);
    }

    // 일정 수정
    @Transactional
    public TodoResponseDto updateTodo(Long id, String title, String todo){
        if(title == null || todo == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "제목 & 내용은 필수입니다." );
        }

    }
}
