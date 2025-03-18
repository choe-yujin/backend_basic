<요구사항 명세화>  
**미션 2: 회원 대출 한도 계산 기능 구현 (학생용 과제)**
- **입력:** LibraryMember 객체 배열 (기본 회원과 학생 회원 포함)
- **처리:**
    1. LibraryMember 클래스를 정의한다.
        - private 멤버 변수: name(String), membershipId(String)
        - 생성자와 getter 메서드를 구현하고, 기본 대출 한도(getBorrowLimit())는 3권을 반환한다.
    2. StudentMember 클래스를 정의하여 LibraryMember를 상속받고, getBorrowLimit()를 오버라이딩하여 5권을 반환하도록 구현한다.
    3. MemberManager 클래스를 작성하여 LibraryMember 배열을 입력받고, for문을 사용하여 각 회원의 정보를  
       "회원: [name], ID: [membershipId], 대출 한도: [borrowLimit]" 형태의 문자열로 결합한 후,  
       세미콜론(;)으로 구분하여 최종 문자열로 반환한다.
- **출력:** 예시 결과:  
  "회원: Alice, ID: LM001, 대출 한도: 3; 회원: Bob, ID: LM002, 대출 한도: 5"
- **목표:** 학생들이 객체지향 기본 개념(클래스, 상속, 메서드 오버라이딩)을 이해하고, 이를 활용하여 간단한 회원 관리 기능을 구현하도록 한다.

<개발 순서>
1. LibraryMember 클래스를 정의하여 private 멤버 변수, 생성자, getter 및 기본 getBorrowLimit() (3권) 메서드를 구현한다.
2. StudentMember 클래스를 정의하여 LibraryMember를 상속하고, getBorrowLimit() 메서드를 오버라이딩하여 5권을 반환하도록 구현한다.
3. MemberManager 클래스를 정의하여 LibraryMember 배열을 입력받고, for문을 사용하여 각 회원의 정보를 문자열로 결합하는 processMembers() 메서드를 구현한다.
4. main() 메서드에서 예제 데이터를 이용하여 결과 문자열을 출력한다.