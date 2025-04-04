package com.example.schedule.dto;

import com.example.schedule.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final Long id;

    private final String name;

    private final String email;

    private final String password;

    @Builder
    public UserResponseDto (User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
