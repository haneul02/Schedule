package com.example.schedule.service;

import com.example.schedule.dto.UserRequestDto;
import com.example.schedule.dto.UserResponseDto;
import com.example.schedule.dto.UserUpdateResponseDto;
import com.example.schedule.entity.User;
import com.example.schedule.repository.UserReqository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReqository userReqository;

    // user 저장
    @Transactional
    public void createUser(UserRequestDto requestDto){
        if (requestDto.getName() == null || requestDto.getEmail() == null || requestDto.getPassword() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "모든 항목을 작성해주세요.");
        }
         User user = requestDto.toEntity();
        userReqository.save(user);
    }

    // 로그인
    @Transactional(readOnly = true)
    public UserResponseDto loginUser(String email, String password) {
        User user = userReqository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다."));

        if (!user.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        return new UserResponseDto(user); // 로그인 성공 시 사용자 정보 반환
    }

    // user 조회
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUser(){
        return userReqository.findAll().stream()
                .map(UserResponseDto::new)
                .toList();
    }

    // user name으로 특정 user 조회
    public UserResponseDto getUserMyName(String name) {
        User user = userReqository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        return new UserResponseDto(user);
    }

    // user 정보 수정 (name, email, password 중에 하나만 수정해도 저장 가능)
    @Transactional
    public UserUpdateResponseDto updateUser(Long id, String name, String email, String password) {
        // id에 해당하는 사용자 찾기
        User user = userReqository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        // 필드가 null이 아닐 경우에만 업데이트
        if (name != null) {
            user.updateName(name);
        }
        if (email != null) {
            user.updateEmail(email);
        }
        if (password != null) {
            user.updatePassword(password);
        }

        userReqository.saveAndFlush(user);

        return new UserUpdateResponseDto(user);
    }

    // user 삭제
    @Transactional
    public void deleteUserById(Long id) {
        User user = userReqository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
        userReqository.delete(user);
    }
}
