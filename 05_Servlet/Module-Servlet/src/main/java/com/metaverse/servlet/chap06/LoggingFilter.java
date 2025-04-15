package com.metaverse.servlet.chap06;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/*
* Filter
* - filter 인터페이스는 서블릿 컨테이너에서 http 요청과 응답을 가로채고
* 전처리(pre-processing) 또는 후처리 (post-processing)을 수행하는 강력한 컴포넌트이다.
*
* - 서블릿 /jsp와 같은 최종 리소스에 도달하기 전/후에 공통 로직(예: 인증.로깅.인코딩)을
* 중앙화하여 코드 중복을 줄이고 유지보수성을 높이는데 목적이 있다.
*
* - 동작 메커니즘
* 1. init(filterconfig config) : 초기화 시 호출되며, 설정 정보를 전달받음.
* 2. dofilter(servletRequest req, ServletResponse res, FilterChain chain) :
* 요청/ 응답 처리의 핵심 메서드로, 체인(chain)을 통해 다음 필터나 리소스로 제어를 전달
* - chain.dofilter(req, res) 호출 전 : 요청 전처리
* - 호출 후 : 응답 후처리
* 3. destroy() : 필터 종료 시 호출되며, 자원 정리 역할
* - 주요 특징
* - 체인 구조 : 여러 필터가 순차적으로 적용되며, @WebFilter의 url 패턴(예 : '/*')으로 매핑
* - 비 침투성 : 대상 서블릿/jsp 코드를 수정하지 않고도 동작 추가 가능
* - 재사용성 : 애플리케이션 전반에 걸친 공통 관심사(cross-cutting concerns)를 모듈화
*
* */
@WebFilter("/*")
public class LoggingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoggingFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        long startTime = System.currentTimeMillis();
        System.out.println("요청 시작 : " + request.getRequestURI());

        /*
        * filterChain.doFilter:
        * - filterchain 객체의 doFilter 메서드는 현재 필터에서 다음 필터 또는 최종 리소스(서블릿/jsp)로
        * 요청(serveltRequest)와 응답(servletResponse)을 전달하는 핵심 메서드이다.
        *
        * - 필터 체인(filter chain)은 여러 필터가 순차적으로 적용되는 구조를 의미하며,
        * 각 필터는 :doFilter:를 호출하여 체인 내 다음 단계로 제어를 넘긴다.
        * */
        filterChain.doFilter(request, servletResponse); // 다음 플터에 요청 넘겨주기.
        long endTime = System.currentTimeMillis();
        System.out.println("요청 종료: " + request.getRequestURI() + ", 소요시간 : " + (endTime - startTime) + "ms");
    }

    @Override
    public void destroy() {
        System.out.println("LoggingFilter destroy");
    }
}
