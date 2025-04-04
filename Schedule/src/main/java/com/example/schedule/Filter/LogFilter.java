package com.example.schedule.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

    // 필터 초기화, 서블릿 컨테이너가 필터를 생성할 때 한 번 호출
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("로그 필터 초기화");
    }

    // 요청과 응답을 중간에서 가로채어(log) 기록하는 역할 = cctv같은 역할
    // 클라이언트 요청이 오면 해당 요청 정볼르 로그에 남김
    // 요청을 처리한 후 응답이 돌아오면 응답 정보를 로그에 남김
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("로그 필터 실행 중");

        // HTTP 요청 객체로 변환
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // 요청된 URI 가져오기
        String requestURI = httpRequest.getRequestURI();
        // 요청 구분하기 위해 UUID 생성
        String uuid = UUID.randomUUID().toString();

        try {
            log.info("요청 시작 : [{}][{}]", uuid, requestURI);
            chain.doFilter(request, response);
        } catch (Exception e) {
            // 필터는 예외를 처리하는 역할이 아니여서 예외가 발생하면 다음 단계로 넘김
            throw e;
        } finally {
            log.info("응답 반환 : [{}][{}]", uuid, requestURI);
        }
    }

    // 필터가 삭제될 때 실행
    @Override
    public void destroy() {
        log.info("로그 필터 제거");
    }
}
