<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>가입 결과</title>
</head>
<body>
<h1>가입 결과</h1>
<p>${message}</p>
<p>가입된 사용자: ${username}</p>
<p>이메일: ${email}</p>
<p>현재 URL: <%= request.getRequestURI() %></p>
<a href="/request">다시 가입</a>
</body>
</html>