**미션 7: 도서 목록 관리 시스템 구축**
- **입력:**
    - 도서 정보를 담은 Book 객체
- **기능:**
    1. Book 객체를 추가, 삭제할 수 있는 도서 목록 관리 기능
    2. 현재 등록된 모든 도서 목록을 조회하여 문자열로 반환
- **포함 기술:**
    - 변수, 데이터 타입, 연산자, 배열과 문자열 다루기
    - 클래스 및 객체, 생성자와 메서드 활용, 캡슐화
    - 제네릭스와 Collection(List) 활용
- **출력 예:**  
  "도서: [Title1], [Author1], [Price1]; 도서: [Title2], [Author2], [Price2]"


**미션 7 (도서 목록 관리 시스템 구축):**
1. `Book` 클래스를 정의하여 private 멤버 변수(title, author, price)를 선언하고, 생성자와 getter, toString() 메서드를 구현
2. `BookManager` 클래스를 정의하여 내부에 `List<Book>` 컬렉션을 선언하고,
    - `addBook(Book book)` 메서드로 도서를 추가
    - `removeBook(String title)` 메서드로 도서를 삭제
    - `listBooks()` 메서드로 전체 도서 목록을 문자열로 반환
3. main() 메서드에서 샘플 데이터를 이용하여 기능을 시연



