package com.example.schedule.dto;

import lombok.Builder;
import lombok.Getter;
import com.example.schedule.entity.User;

@Getter
public class UserUpdateResponseDto {

    private final Long id;

    private final String name;

    private final String email;

    private final String password;

    @Builder
    public UserUpdateResponseDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
