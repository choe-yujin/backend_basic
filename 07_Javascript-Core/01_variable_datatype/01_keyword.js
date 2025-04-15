/*
변수
변수(variable)는 데이터를 저장할 때 쓰이는 ‘이름이 붙은 저장소’이다.
이러한 변수는 원래 메모리라는 공간에 (@550023)와 같이 주소값으로 표시를 해야한다.
그러나 이러한 값으로 변수를 사용하는 것은 매우 어렵기 때문에 이것을 쉽게 표현할 수 있도록
변수명이라는 것을 사용하여 부르게 된다.
즉, 변수명은 프로그램의 메모리와 연결이 되어 있으며 메모리에 데이터가 저장되는 것이다.

변수를 생성할때 우리는 변수를 선언한다고 표현한다.
변수 선언은 다음과 같은 규칙을 갖게된다.

키워드 변수명;
키워드 : 변수의 접근 범위를 지정함.
변수명 : 변수를 부를 이름을 설정함.
*/


// let: 값을 변경할 수 있는 변수 선언
let greeting = "Hello, Node.js!";
console.log(greeting); // 출력: Hello, Node.js!

greeting = "Welcome to JavaScript!";
console.log(greeting); // 출력: Welcome to JavaScript!

// const: 변경할 수 없는 변수 선언
const pi = 3.14159;
console.log(pi); // 출력: 3.14159

// const로 선언한 변수는 값을 재할당할 수 없다.
// pi = 3.14; // 오류 발생: Assignment to constant variable.



/*
# 2. 변수의 스코프
스코프는 변수가 어디에서 접근 가능한지를 결정한다.
Node.js 환경에서의 스코프는 전역 스코프와 블록 스코프가 있다.
*/

// 블록 스코프
{
    let blockScoped = "블록 안에서만 접근 가능";
    console.log(blockScoped); // 출력: 블록 안에서만 접근 가능
}
// console.log(blockScoped); // 오류 발생: blockScoped is not defined


// 전역 스코프
{
    var globalScoped = "전역에서 접근 가능";
}
console.log(globalScoped); // 출력: 전역에서 접근 가능


/*
3. 변수 선언 규칙과 스타일
- 변수 이름은 알파벳, 숫자, _, $만 사용할 수 있다.
- 변수 이름은 숫자로 시작할 수 없습니다.
- 카멜케이스(camelCase)를 사용하는 것이 일반적이이다.
- 예약어 사용 금지: 변수 이름으로는 자바스크립트의 예약어를 사용하지 않는다. 
   예약어 이미 지정되어 있는 문자
- 대소문자 구분: 변수 이름에서 대소문자를 구분한다 A, a 둘은 다른 변수.
- 의미 있는 이름 사용: 변수 이름은 그 변수의 역할이나 내용을 명확하게 나타내는 것이 좋다.
- 상수 변수: 상수를 선언할 때는 일반적으로 대문자와 언더스코어를 사용하는 것이 일반적이다.
*/

// 잘못된 변수 선언 예시
// let 1name = "Alice"; // 오류: 변수 이름은 숫자로 시작할 수 없습니다.


// 올바른 변수 선언
let userName = "Alice"; // 카멜케이스 스타일
const userAge = 25;

console.log(`사용자 이름: ${userName}, 나이: ${userAge}`); // 출력: 사용자 이름: Alice, 나이: 25