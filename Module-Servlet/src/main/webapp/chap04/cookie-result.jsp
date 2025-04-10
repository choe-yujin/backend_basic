<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>쿠키 결과</title>
</head>
<body>
<h1>쿠키 데모</h1>
<p>쿠키에 저장된 값: ${savedValue}</p>
<div>
  <pre>설명: 첫 번째 요청에서 쿠키를 읽을 수 없는 이유
  - 쿠키는 클라이언트(브라우저)에 저장되며, 서버가 `resp.addCookie()`로 쿠키를 설정한 후
    클라이언트가 응답을 받은 시점에 브라우저에 저장된다.
       - 따라서 첫 번째 POST 요청에서 `doPost`가 쿠키를 설정하고 바로 `doGet`을 호출해
         JSP로 포워딩할 때, 아직 클라이언트가 쿠키를 받지 않은 상태이다.
       - `req.getCookies()`는 이전 요청에서 클라이언트가 보낸 쿠키만 읽으므로,
         첫 요청에서는 `userValue` 쿠키가 없어 "없음"으로 표시된다.
       - 두 번째 요청(예: 새로고침 또는 링크 클릭)부터 브라우저가 쿠키를 서버에 전송하므로 읽을 수 있다.

       F12 개발자 도구로 정상 발행 확인 방법:
       1. 브라우저에서 F12를 눌러 개발자 도구를 열고
       2. 'Application' 탭(Chrome) 또는 'Storage' 탭(Firefox)을 선택.
       3. 왼쪽 메뉴에서 'Cookies'를 확장하고, 현재 도메인(예: http://localhost:8080)을 선택.
       4. 쿠키 목록에서 'userValue'라는 이름의 쿠키가 보이고, 'Value' 열에 입력한 값(예: "test")이
          표시되면 쿠키가 정상적으로 발행된 것이다.
       5. 'Expires/Max-Age' 열에 1시간(3600초) 후의 시간이 설정되어 있는지도 확인하세요.
  </pre>
</div>
<a href="/chap04/index.html">돌아가기</a>
</body>
</html>