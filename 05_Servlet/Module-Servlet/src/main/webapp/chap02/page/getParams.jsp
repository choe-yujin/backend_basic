<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--
< %@ % > 는 JSP 지시자(directive)로, 페이지 설정을 정의한다.
page 지시자는 이 JSP 페이지의 속성을 지정하는데 사용된다.
contentType="text/html;charset=UTF-8": 이 페이지가 HTML 형식으로 출력되며, 문자 인코딩을 UTF-8로 설정한다.
language="java": 이 JSP 페이지에서 사용할 스크립팅 언어가 Java임을 나타낸다.
-->
<html>
<head>
    <title>GET 파라미터 확인</title>
</head>
<body>
<h1>GET 요청 파라미터 결과</h1>
<!--
< %= %>는 JSP 표현식(expression)으로, Java 코드의 결과값을 HTML에 출력한다.
request는 JSP의 내장 객체(implicit object)로, 클라이언트의 요청 정보를 담고 있다.
getAttribute("method"): request 객체에 저장된 "method"라는 이름의 속성 값을 가져온다.
이 값은 서블릿에서 setAttribute()로 설정된 데이터다.
-->
<p><strong>요청 메서드:</strong> <%= request.getAttribute("method") %></p>
<p><strong>쿼리스트링 - 이름:</strong> <%= request.getAttribute("name") %></p>
<p><strong>쿼리스트링 - 나이:</strong> <%= request.getAttribute("age") %></p>
<p><strong>경로 값:</strong> <%= request.getAttribute("pathValue") %></p>
</body>
</html>