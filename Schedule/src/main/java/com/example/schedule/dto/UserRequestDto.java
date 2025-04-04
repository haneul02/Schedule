package com.example.schedule.dto;

import com.example.schedule.entity.User;
import lombok.Getter;

@Getter
public class UserRequestDto {

    private final String name;

    private final String email;

    private final String password;

    public UserRequestDto(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
