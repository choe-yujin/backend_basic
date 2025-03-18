<요구사항 명세화>  
**미션 5: 도서 검색 기능 구현 (인터페이스 활용)**
- **입력:**
    - 키워드 문자열과 Book 객체 배열
- **처리:**
    1. `Searchable` 인터페이스를 정의하여 `boolean matches(String keyword)` 메서드를 선언한다.
    2. `Book` 클래스를 정의한다.
        - private 멤버 변수: title(String), author(String), price(double)
        - 생성자와 getter, toString() 메서드를 구현한다.
        - `Searchable` 인터페이스를 구현하여, `matches(String keyword)` 메서드에서 도서 제목에 키워드가 포함되어 있으면 true를 반환한다.
    3. `LibrarySearch` 클래스를 정의한다.
        - 메서드 `String searchBooks(Book[] books, String keyword)`를 구현하여, for문을 사용해 배열을 순회하며 각 Book의 `matches()` 메서드를 호출한다.
        - 일치하는 도서들의 정보를 세미콜론(;)으로 구분하여 연결한 결과 문자열을 반환한다.
- **출력:**
    - 검색 결과에 해당하는 도서 정보 문자열 (일치하는 도서가 없으면 빈 문자열 반환)
- **포함 기술:**  
  변수, 데이터 타입, 연산자, 배열과 문자열 다루기, 클래스 및 객체, 생성자와 메서드 활용, 캡슐화, 상속, 다형성, 인터페이스
- **JUnit 활용:**  
  @BeforeEach, @AfterEach, @Test, assertEquals, assertTrue

<개발 순서>
1. `Searchable` 인터페이스를 정의한다: `boolean matches(String keyword)`
2. `Book` 클래스를 정의한다:
    - private 멤버 변수 (title, author, price)
    - 생성자, getter, toString() 메서드
    - `matches(String keyword)` 메서드 구현 (키워드가 title에 포함되어 있으면 true)
3. `LibrarySearch` 클래스를 정의한다:
    - 메서드 `String searchBooks(Book[] books, String keyword)` 구현
    - for문을 사용해 배열 순회 및 검색 결과 문자열 생성
4. main() 메서드를 작성하여, 샘플 Book 배열과 검색 키워드를 사용해 결과를 출력한다.