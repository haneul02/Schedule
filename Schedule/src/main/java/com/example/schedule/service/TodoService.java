package com.example.schedule.service;

import com.example.schedule.dto.TodoRequestDto;
import com.example.schedule.dto.TodoResponseDto;
import com.example.schedule.dto.TodoUpdateResponseDto;
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
    public void createSchedule(TodoRequestDto requestDto) {
        // 제목 일정이 없음 예외 처리
        if (requestDto.getTitle() == null || requestDto.getSchedule() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "모든 항목을 작성해주세요.");
        }
        // DTO를 엔티티로 변환 후 저장
        Todo todo = requestDto.toEntity();
        todoReqository.save(todo);
    }

    // 전체 일정 조회
    @Transactional(readOnly = true)
    public List<TodoResponseDto> getAllTodo() {
        // 모든 일정 엔티티를 DTO로 변화하여 반환
        return todoReqository.findAll().stream()
                .map(TodoResponseDto::new)
                .toList();
    }

    // 특정 일정 조회
    @Transactional(readOnly = true)
    public TodoResponseDto getTodoMyId(Long id) {
        // ID로 일정 조회, 없으면 예외 처리
        Todo todo = todoReqository.findById(id)
                .orElseThrow(IdException::new);
        return new TodoResponseDto(todo);
    }

    // 일정 수정(제목 & 일정)
    @Transactional
    public TodoUpdateResponseDto updateTodo(Long id, String title, String schedule) {
        // 제목과 일정이 없으면 예외 발생
        if (title == null || schedule == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "제목 & 내용은 필수입니다.");
        }
        // ID로 일정 조회, 없으면 예외 처리
        Todo todo = todoReqository.findById(id)
                .orElseThrow(IdException::new);

        // 일정 내용 업데이트
        todo.update(title, schedule);
        return new TodoUpdateResponseDto(todo);
    }

    // 일정 제목만 수정
    @Transactional
    public TodoUpdateResponseDto updateTitle(Long id, String title) {
       // 제목 없음 예외 처리
        if (title == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "제목은 필수입니다.");
        }
        // ID로 일정 조회, 없으면 예외 처리
        Todo todo = todoReqository.findById(id)
                .orElseThrow(IdException::new);
        // 제목만 업데이트
        todo.updateTitle(title);
        return new TodoUpdateResponseDto(todo);
    }

    // 일정 삭제
    @Transactional
    public void deleteTodo(Long id) {
        // ID로 일정 조회, 없으면 예외 처리
        Todo todo = todoReqository.findById(id)
                .orElseThrow(IdException::new);
        // 일정 삭제
        todoReqository.delete(todo);
    }
}
