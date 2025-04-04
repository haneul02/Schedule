package com.example.schedule.Filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class LoginCheckFilter implements Filter {

    private final SessionManager sessionManager;

    // 로그인 없이 접근 가능한 리스트
    private static final String[] WHITE_LIST = {"/user/login", "/logout", "/user/signup", "/user/name/"};

    // 사용자의 요청이 인증이 필요한 경로인지 확인
    // 필요하면 로그인 여부 체크
    // 로그인 되어 있으면 Unauthorized 응답 반환
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("인증 체크 필터 실행: {}", requestURI);

            // 인증이 필요한 요청인지 확인
            if (isLoginCheckPath(requestURI)) {
                Long userId = sessionManager.getSession(httpRequest);

                if (userId == null) { // 로그인 안 된 사용자
                    log.info("미인증 사용자 요청 {}", requestURI);
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "인증이 필요합니다."); // 에러 반환
                    return; // 더이상 진행하지 않고 요청 종료
                }
            }

            chain.doFilter(request, response); // 로그인된 사용자면 다음 필터로 요청 전달

        } catch (Exception e) {
            throw e; // 필터는 예외를 처리하는 역할이 아니여서 예외가 발생하면 다음 단계로 넘김
        } finally {
            log.info("인증 체크 필터 종료: {}", requestURI);
        }
    }

    // 로그인 체크가 필요한 경로인 확인
    // 로그인 없이 접근 가능한 URL에 포함되지 않으면 true 반환 ( 로그인 체크가 필요하다)
    private boolean isLoginCheckPath(String requestURI) {
        for (String path : WHITE_LIST) {
            if (requestURI.startsWith(path)) {
                return false; // 화이트리스트에 포함되면 로그인 체크 필요 없음
            }
        }
        return true; // 화이트리스트에 포함되지 않으면 로그인 체크 필요
    }
}
