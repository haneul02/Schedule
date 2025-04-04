package com.example.schedule.controller;

import com.example.schedule.Filter.LoginForm;
import com.example.schedule.Filter.SessionManager;
import com.example.schedule.dto.UserRequestDto;
import com.example.schedule.dto.UserResponseDto;
import com.example.schedule.dto.UserUpdateResponseDto;
import com.example.schedule.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SessionManager sessionManager;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserRequestDto requestDto) {
        userService.createUser(requestDto);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginForm loginForm, HttpServletResponse response) {
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();

        UserResponseDto user = userService.getAllUser().stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (user == null) {
            return ResponseEntity.status(401).body("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        sessionManager.createSession(user.getId(), response);
        return ResponseEntity.ok("로그인 성공");
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        sessionManager.expire(request, response);
        return ResponseEntity.ok("로그아웃되었습니다.");
    }

    // 모든 사용자 조회
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    // 특정 사용자 조회 (이름으로 검색)
    @GetMapping("/name/{name}")
    public ResponseEntity<UserResponseDto> getUserByName(@PathVariable String name) {
        return ResponseEntity.ok(userService.getUserMyName(name));
    }

    // 사용자 정보 수정
    @GetMapping("/{id}")
    public ResponseEntity<UserUpdateResponseDto> updateUser(@PathVariable Long id, HttpServletRequest request,
                                                            @RequestParam(required = false) String name,
                                                            @RequestParam(required = false) String email,
                                                            @RequestParam(required = false) String password) {

        System.out.println( "id = " + id + ", name = " + name + ", email = " + email + ", password = " + password );
        return ResponseEntity.ok(userService.updateUser(id, name, email, password));
    }

    // 사용자 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("사용자가 삭제되었습니다.");
    }
}
