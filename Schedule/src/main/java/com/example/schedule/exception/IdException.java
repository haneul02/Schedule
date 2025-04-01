package com.example.schedule.exception;

public class IdException extends RuntimeException {
    public IdException(){
        super("해당 id가 없습니다.");
    }
}
