<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<h1>회원가입</h1>
<%
    String error = (String) session.getAttribute("error");
    if (error != null) {
%>
<p style="color: red;"><%= error %></p>
<% session.removeAttribute("error"); // 오류 메시지 표시 후 세션에서 제거 %>
<% } %>
<form action="/request" method="post">
    <label>사용자 이름: <input type="text" name="username"></label><br>
    <label>이메일: <input type="text" name="email"></label><br>
    <button type="submit">가입</button>
</form>
</body>
</html>