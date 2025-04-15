


# 서블릿(Servlet)
## 1. 서블릿(Servlet)이란?
서블릿은 Java를 사용하여 웹 애플리케이션을 개발할 때 클라이언트의 요청을 처리하고 응답을 생성하는 서버 측 프로그램이다.<br>
간단히 말해, 웹 서버에서 동작하는 Java 클래스이다.
<img src="https://miro.medium.com/v2/resize:fit:1100/format:webp/0*HoXNlt79M4R0Imbq.PNG"/>
### 주요 특징
- 자바 기반의 웹 컴포넌트
- 클라이언트-서버 구조에서 서버 측 처리를 담당
- HTTP 요청에 대한 동적 응답 생성 가능
- 멀티스레드로 동작하여 다수의 요청을 효율적으로 처리

## 2. HTTP 프로토콜 기초
### HTTP란?
HTTP(Hypertext Transfer Protocol)는 웹에서 데이터를 주고받기 위한 프로토콜로 클라이언트가 서버에 요청(Request)을 보내면, 서버는 이에 대한 응답(Response)을 반환한다.

### HTTP 요청/응답 구조
- **요청(Request)**
    - 요청 라인: 메서드(GET, POST 등), URL, HTTP 버전
    - 헤더: 요청에 대한 부가 정보
    - 본문: 요청 데이터(POST 요청 시)

- **응답(Response)**
    - 상태 라인: HTTP 버전, 상태 코드(200, 404 등), 상태 메시지
    - 헤더: 응답에 대한 부가 정보
    - 본문: 실제 응답 데이터(HTML, JSON 등)

## 3. 웹 서버와 WAS의 개념
### 웹 서버(Web Server)
웹 서버는 클라이언트로부터 HTTP 요청을 받아 정적 리소스(HTML, CSS, JavaScript, 이미지 등)를 제공하는 서버이다.<br>
대표적인 웹 서버로는 Apache, Nginx 등이 있다.

### WAS(Web Application Server)
WAS는 웹 서버의 기능과 함께 동적 컨텐츠를 생성하는 애플리케이션 로직을 수행하는 미들웨어이며, Java 기반의 대표적인 WAS로는 Tomcat, JBoss, WebLogic 등이 있다.

### 웹 서버와 WAS의 차이점
- **웹 서버**: 정적 리소스 처리에 특화
- **WAS**: 동적 컨텐츠 생성 및 비즈니스 로직 처리 가능
  <img src="https://taes-k.github.io/images//posts/trick_basic/2019-05-24-webserver/1.png"/>

## 4. 서블릿 컨테이너(Servlet Container)
### 서블릿 컨테이너란?
서블릿 컨테이너는 서블릿의 생명주기를 관리하고, 클라이언트의 요청을 서블릿에 전달하며, 서블릿의 응답을 클라이언트에게 전달하는 역할을 한다. Tomcat, Jetty 등이 대표적인 서블릿 컨테이너이다.

### 서블릿 컨테이너의 주요 기능
1. **생명주기 관리**: 서블릿의 초기화, 요청 처리, 소멸까지의 과정 관리
2. **통신 지원**: 클라이언트-서블릿 간의 통신 처리
3. **멀티스레딩 관리**: 다중 요청 처리를 위한 스레드 관리
4. **선언적 보안 관리**: 설정을 통한 보안 처리

## 5. Tomcat
Tomcat은 Apache Software Foundation에서 개발한 오픈소스 WAS로, 서블릿 컨테이너의 대표적인 구현체이다.

### Tomcat의 주요 특징
- 경량화된 WAS
- 독립 실행 가능
- 다양한
  운영체제 지원
- 서블릿과 JSP 스펙 구현
- 웹 서버(Apache)와 연동 가능

### Tomcat의 구조
- **Server**: Tomcat의 전체 인스턴스
- **Service**: 하나 이상의 Connector와 하나의 Engine으로 구성
- **Connector**: 다양한 프로토콜로 들어오는 요청을 처리
- **Engine**: 요청에 대한 처리를 담당
- **Host**: 가상 호스트
- **Context**: 웹 애플리케이션

## 6. 서블릿의 동작 원리
### 서블릿의 생명주기
1. **초기화(init)**: 서블릿 인스턴스가 생성된 후 초기화될 때 호출
2. **서비스(service)**: 클라이언트 요청이 들어올 때마다 호출
3. **소멸(destroy)**: 서블릿 인스턴스가 소멸될 때 호출

### 서블릿 요청 처리 흐름
1. 클라이언트가 HTTP 요청을 보냄
2. 웹 서버가 요청을 받아 WAS로 전달
3. WAS 내의 서블릿 컨테이너가 HttpServletRequest, HttpServletResponse 객체 생성
4. web.xml 또는 어노테이션을 참조하여 해당 서블릿을 찾음
5. 서블릿의 service() 메서드 호출 (내부적으로 doGet(), doPost() 등 호출)
6. 서블릿이 요청을 처리하고 HttpServletResponse 객체에 응답 작성
7. WAS가 HttpServletResponse 객체를 HTTP 응답으로 변환하여 클라이언트에게 전송
8. 응답이 끝나면 HttpServletRequest, HttpServletResponse 객체 소멸

## 7. 서블릿 구현 방법
### 기본 서블릿 작성법
```java
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class HelloServlet extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
        // 서블릿 초기화 코드
        System.out.println("HelloServlet 초기화");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // GET 요청 처리 로직
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello, Servlet!</h1>");
        out.println("</body></html>");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // POST 요청 처리 로직
    }
    
    @Override
    public void destroy() {
        // 서블릿 소멸 시 실행 코드
        System.out.println("HelloServlet 소멸");
    }
}
```

### 서블릿 매핑 설정 (web.xml)
```xml
<web-app>
    <servlet>
        <servlet-name>hello</servlet-name>
        <servlet-class>HelloServlet</servlet-class>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
</web-app>
```

### 어노테이션을 사용한 서블릿 매핑 (Servlet 3.0 이상)
```java
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    // 서블릿 코드
}
```

## 8. 서블릿 API 주요 클래스 및 인터페이스

### 주요 클래스
- **HttpServlet**: 서블릿을 구현하기 위한 추상 클래스
- **HttpServletRequest**: 클라이언트 요청 정보를 담는 객체
- **HttpServletResponse**: 서버 응답 정보를 담는 객체
- **ServletConfig**: 서블릿 초기화 파라미터 정보
- **ServletContext**: 웹 애플리케이션 전체에서 공유되는 정보

### 주요 인터페이스
- **Servlet**: 모든 서블릿이 구현해야 하는 기본 인터페이스
- **ServletRequest**: 클라이언트 요청 정보에 접근하기 위한 인터페이스
- **ServletResponse**: 클라이언트 응답을 생성하기 위한 인터페이스

## 9. 서블릿의 요청 처리 과정 상세
### 1. 요청 분석 및 파라미터 추출
```java
// GET 요청의 쿼리 파라미터 추출
String name = request.getParameter("name");

// POST 요청의 폼 데이터 추출
String email = request.getParameter("email");
```

### 2. 세션 관리
```java
// 세션 획득 (없으면 생성)
HttpSession session = request.getSession();

// 세션에 데이터 저장
session.setAttribute("userId", userId);

// 세션에서 데이터 조회
String userId = (String) session.getAttribute("userId");

// 세션 만료
session.invalidate();
```

### 3. 쿠키 처리
```java
// 쿠키 생성 및 응답에 추가
Cookie cookie = new Cookie("userName", "John");
cookie.setMaxAge(3600);  // 1시간
response.addCookie(cookie);

// 요청에서 쿠키 읽기
Cookie[] cookies = request.getCookies();
for (Cookie cookie : cookies) {
    if ("userName".equals(cookie.getName())) {
        String userName = cookie.getValue();
        break;
    }
}
```

### 4. 응답 생성
```java
// 응답 콘텐츠 타입 설정
response.setContentType("text/html;charset=UTF-8");

// 응답 상태 코드 설정
response.setStatus(HttpServletResponse.SC_OK);  // 200

// 응답 헤더 설정
response.setHeader("Cache-Control", "no-cache");

// 응답 본문 작성
PrintWriter out = response.getWriter();
out.println("<html><body>");
out.println("<h1>응답 예제</h1>");
out.println("</body></html>");
```

## 10. 서블릿의 장단점
### 장점
- Java의 모든 API 사용 가능
- 플랫폼 독립적
- 강력한 예외 처리
- 멀티스레딩 지원으로 효율적인 요청 처리
- 보안 관리 용이

### 단점
- 화면 구성(View)와 비즈니스 로직이 혼합될 수 있음
- 코드 변경 시 재컴파일 및 재배포 필요
- HTML 생성이 번거로움

## 11. 서블릿 관련 패턴 및 발전

### MVC 패턴
서블릿과 JSP를 사용하여 모델(Model), 뷰(View), 컨트롤러(Controller)로 분리하는 아키텍처 패턴이다.
1. **모델(Model)**: 비즈니스 로직과 데이터 처리
2. **뷰(View)**: 사용자에게 보여지는 화면 (주로 JSP)
3. **컨트롤러(Controller)**: 사용자 요청을 처리하고 모델과 뷰를 연결 (주로 서블릿)

### 프론트 컨트롤러 패턴
모든 요청을 하나의 컨트롤러가 받아서 처리하는 패턴이다. 많은 웹 프레임워크의 기본 구조이다.
<img src="https://blog.kakaocdn.net/dn/bcff5H/btstbdRuSr9/pNKnGdMwftSWmiGLHA7yL0/img.png"/>

### 서블릿의 발전과 프레임워크
- **Spring MVC**: 서블릿 기반의 강력한 웹 프레임워크
- **JSF(JavaServer Faces)**: 컴포넌트 기반 UI 프레임워크
- **Struts**: 서블릿과 JSP 기반의 MVC 프레임워크

## 12. 서블릿 개발 시 주의사항 및 팁
### 주의사항
1. **스레드 안전성**: 서블릿은 멀티스레드 환경에서 동작하므로 인스턴스 변수 사용 시 주의
2. **리소스 관리**: 데이터베이스 연결 등의 리소스는 사용 후 반드시 닫기
3. **예외 처리**: 적절한 예외 처리로 서버 안정성 확보
4. **세션 관리**: 세션 데이터 최소화 및 적절한 타임아웃 설정

### 개발 팁
1. **로깅 활용**: 디버깅과 모니터링을 위한 로깅 구현
2. **필터 활용**: 공통 기능(인증, 인코딩 등)은 필터로 구현
3. **설정 분리**: 환경별 설정 정보는 외부 파일로 분리
4. **RESTful API**: 현대적인 웹 애플리케이션을 위한 RESTful API 설계

## 요약

서블릿은 Java 기반 웹 애플리케이션 개발의 기초가 되는 기술이며, HTTP 요청에 대한 동적 처리를 가능하게 하며, WAS 환경에서 실행됩니다. 웹 서버, WAS, 서블릿 컨테이너, HTTP 프로토콜에 대한 이해를 바탕으로 서블릿의 생명주기와 요청 처리 흐름을 파악하면 웹 애플리케이션 개발의 근본을 이해할 수 있습니다. 현대 웹 프레임워크(Spring 등)의 많은 개념이 서블릿을 기반으로 하고 있으므로, 서블릿에 대한 이해는 Java 웹 개발자에게 필수적이다.
