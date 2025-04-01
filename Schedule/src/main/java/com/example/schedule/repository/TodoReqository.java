package com.example.schedule.repository;

import com.example.schedule.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoReqository extends JpaRepository<Todo, Long> {
}
