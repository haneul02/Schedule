package com.example.schedule.dto;

import com.example.schedule.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserUpdateRequestDto {

    private final String name;

    private final String email;

    private final String password;

    public UserUpdateRequestDto(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
