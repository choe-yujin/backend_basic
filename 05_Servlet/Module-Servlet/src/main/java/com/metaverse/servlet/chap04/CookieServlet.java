package com.metaverse.servlet.chap04;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
* 쿠키
* - 쿠키는 클라이언트와 서버 간에 상태 정보를 유지하기 위해 사용되는 작은 데이터 조각이다.
* - http는 기본적으로 stateless(상태 비저장) 프로토콜이므로, 쿠키를 통해 사용자 세션, 설정값을 저장한다.
* - 구조
* - 이름과 값 쌍으로 구성되며, 선택적으로 만료 시간, 경로, 도메인 등의 속성을 가질 수 있게 된다.
* - 예 "userValue=홍길동" 처럼 key-value 형식으로 저장된다.
*
* - 동작:
* 1. 서버가 "httpServletResponse"를 통해 "addCookie()"로 쿠키를 설정하면, 브라우저에 저장된다
* 2. 이후 요청 시 브라우저가 "Cookie" 헤더에 쿠키를 포함해 서버로 전송한다.
* 3. 서버는 "httpServletRequest.getCookies()로 쿠키를 읽어서 활용한다.
* - 특징
* - 클라이언트 측에 저장되기 때문에 보안에 취약하다.
* - 크기에 제한이 있다. (보통 4kb 내외)
* - 세션 key 관리, 사용자 추적, 설정 기억 등에 주로 사용한다.
*
* 서버가 클라이언트한테 보내주는 데이터-클라이언트의 쿠키저장소에 저장해두고 관리
* 서버에서는 stateless라고 해서 상태 연결하지 않고 요청
* http프로토콜은 요청-응답으로 끊어짐
* 매번 페이지 이동하는 요청시마다 누군지 알 수 없다.
* 식별할 수 있는 방법 필요
* 사용자에 저장된 데이터가, 어떤 사용자 것인지 알아야 정보 매칭 시킬 수 있다.
* 쿠키 값을 기준으로 세션에 접근할 수 있는 키 값 주고
* 사용자의 http 요청 헤더에 쿠키값 읽고
* 세션 아이디 있다고 하면
* 세션 아이디를 세션 저장소에서 찾고
* 서버가 상태를 기억할 수 있게끔 만들어준다.
* 서버에서는 이 클라이언트의 스토리지에 접근할 수 없음.
* 몇번 페이지에 얼만큼 체류했다거나, 이런 활동 로그를 추적할 수도 있고
*
* */
@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        String savedValue = "없음";
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if("userValue".equals(cookie.getName())) {
                    savedValue = cookie.getValue();
                    break;
                }
            }
        }

        req.setAttribute("savedValue", savedValue);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/chap04/cookie-result.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String cookieValue = req.getParameter("cookieValue");

        if(cookieValue != null && !cookieValue.isEmpty()) {
            cookieValue = cookieValue.replaceAll("[;,\\s]", "");

            /*
            * Cookie 객체
            * - 클래스로 클라이언트(브라우저)와 서버 간 상태 정보를 저장하고 전달하기 위한 객체이다.
            * - HTTP의 stateless 특성을 보완하며, 주로 사용자 데이터나 설정을 유지하는데 사용한다.
            * - 주요 속성과 메서드
            * - 생성자 : Cookie(String name, String value)로 이름과 값을 설정
            * - setMaxAge(int seconds) : 쿠키의 유효 기간(초 단위) 설정. 음수이면 브라우저 종료시 만료, 0이면 즉시 삭제
            * - getName() : 쿠키의 이름을 반환
            * - getValue() : 쿠키의 값 반환
            * - setPath(String url) : 쿠키가 적용될 경로를 지정
            *
            * - 동작 :
            * 1. 서버에서 "cookie" 객체를 생성하고 "HttpServletResponse.addCookie()"로 클라이언트에 전송
            * 2. 클라이언트가 다음 요청 시 "Cookie" 헤더에 쿠키를 포함해서 서버로 전달함
            * 3. 서버는 "HttpServletRequest.getCookies()"로 쿠키 배열을 받아 필요한 쿠키를 추출
            * - 주의
            * - 쿠키 값은 문자열로만 저장되며, 특수 문자(예: 세미콜론, 쉼표)는 제거하거나 인코딩 필요
            * - 쿠키 제한(약 4kb)가 있으므로 대용량 데이터에는 적합하지 않음
            * */
            Cookie cookie = new Cookie("userValue", cookieValue);
            cookie.setMaxAge(3600); // 1시간
            resp.addCookie(cookie);
        } else {
            req.setAttribute("error", "쿠키 값이 입력되지 않았습니다.");
        }
        doGet(req, resp);
    }
}
