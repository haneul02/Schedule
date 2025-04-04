package com.example.schedule.Filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    public static final String SESSION_COOKIE_NAME = "mySessionId";
    private final Map<String, Long> sessionStore = new ConcurrentHashMap<>(); // 사용자 ID만 저장

    // 세션 생성 (userId만 저장)
    public void createSession(Long userId, HttpServletResponse response) {
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, userId);

        Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    // 세션 조회 (userId 반환)
    public Long getSession(HttpServletRequest request) {
        Cookie cookie = findCookie(request, SESSION_COOKIE_NAME);
        if (cookie == null) {
            return null;
        }
        return sessionStore.get(cookie.getValue());
    }

    // 세션 만료 (쿠키까지 삭제)
    public void expire(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = findCookie(request, SESSION_COOKIE_NAME);
        if (cookie != null) {
            sessionStore.remove(cookie.getValue());
            deleteCookie(response);
        }
    }

    // 쿠키 찾기
    private Cookie findCookie(HttpServletRequest request, String cookieName) {
        if (request.getCookies() == null) {
            return null;
        }
        return Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }

    // 쿠키 삭제
    private void deleteCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(SESSION_COOKIE_NAME, null);
        cookie.setMaxAge(0);  // 쿠키 즉시 삭제
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
