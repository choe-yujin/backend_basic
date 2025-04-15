package com.metaverse.servlet.chap02;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/params/*")
public class ParamServlet extends HttpServlet {

    /*
    * 쿼리스트링 파라미터란?
    * - http 요청의 url 뒤에 붙는 "?key=value&key2=value2" 형태의 데이터를 의미한다.
    * - 주로 GET 방식에서 사용되며, 클라이언트가 서버로 데이터를 전달할 때 url에 포함된다.
    * - 예 http://example.com/test?number=11&name=홍길동"에서 "number"와 "name"이 쿼리스트링 파라미터가 된다.
    * - 특징
    * - 데이터가 url에 노출되어 보안에 취약할 수 있다.
    * - 길이 제한이 존재(브라우저나 서버에 따라 다르며 보통은 2000자 내외이다.)
    * - "httpServletRequest.getParameter("key")메서드로 쉽게 추출이 가능하다.
    * */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        /*
        * 쿼리스트링에서 두 개의 파라미터 가져오기
        * getParameter : 요청에서 특정 이름의 파라미터 값을 가져옴
        * - get 요청에서는 쿼리스트링(?name=jone&age=25)에서 값을 추출
        * - 파라미터가 없으면 null 반환
        * */
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        if (name == null) name = "기본이름";
        if (age == null) age = "기본나이";

        // 2. 경로 값(Path Value) -> GET /users/1?where=name
        String pathInfo = req.getPathInfo(); // "/params" 뒤의 경로 (예: /params/123 -> "123")
        String pathValue = (pathInfo != null && pathInfo.length() > 1) ? pathInfo.substring(1) : "기본경로";

        // 3. 데이터 요청 객체에 저장
        req.setAttribute("method", "GET");
        req.setAttribute("name", name);
        req.setAttribute("age", age);
        req.setAttribute("pathInfo", pathInfo);
        req.setAttribute("pathValue", pathValue);

        // 4. 결과 페이지로 포워딩
        // getRequestDispatcher : 지정된 경로로 요청을 전달 RequestDispatcher 객체를 반환
        // - 경로 /chap02/page/getParams.jsp 에 해당하는 리소스(jsp, 서블릿 등)로 제어를 넘기기 위한 준비
        RequestDispatcher dispatcher = req.getRequestDispatcher("/chap02/page/getParams.jsp");

        /*
        * RequestDispatcher : 요청을 다른 리소스로 전달하거나 포함시키는 객체
        * - forward: 메서드를 제공하며 현재 요청과 응답을 다른 리소스로 전달
        * - 서버 내부에서 처리되며, 클라이언트는 url 변경을 인지하지 않음
        * - 요청 속성 (req.setAttribute 로 저장한 데이터)가 유지됨
        * */
        dispatcher.forward(req, resp);
    }


    /*
    * post 방식 body에 파라미터 저장
    * - HTTP 요청 본문(body)에 데이터를 포함하여 서버로 전송하는 방식이다.
    * - 주로 post 메서드에서 사용되며, 쿼리스트링과 달리 url에 데이터가 노출되지 않는다.
    * - 예) html폼에서 <form method="post">를 통해 "key1=value&key2=value" 형태로 body에 데이터가 전송된다.
    * - 특징
    * - 데이터가 요청 본문에 포함되므로 보안성이 쿼리스트링보다 높음
    * - 길이 제한이 없어 대용량 데이터 전송에 적합하다.
    * - httpServletRequest.getParameter("key")로 동일하게 추출하여 사용이 가능하다.
    *   필요시 "getInputStream()"으로 원시 데이터를 읽을 수 있다.
    * - 예시 : 로그인 폼 데이터(아이디, 비밀번호) 전송 시 주로 활용
    * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username == null) username = "기본 사용자";
        if(password == null) password = "기본 비밀번호";

        //2. 데이터 요청 객체 저장
        req.setAttribute("username", username);
        req.setAttribute("password", password);
        req.setAttribute("method", "POST");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/chap02/page/postParams.jsp");
        //doGet(req, resp);
        dispatcher.forward(req, resp);
    }
}
