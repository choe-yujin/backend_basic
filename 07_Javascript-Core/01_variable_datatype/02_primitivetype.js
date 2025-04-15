/*
수업 목표 : 변수가 무엇인지 이해하고 변수 선언과 할당을 적용한다.

1. 원시 자료형과 메모리
원시 자료형(Primitive Data Type)은 프로그래밍 언어에서 가장 기본적인 데이터 유형으로,
더 이상 나눌 수 없는 단일 값을 가진다. 
각 값은 불변(immutable)하며, 값을 복사하면 새로운 메모리 공간에 저장된다.

EX) : 
Number	숫자 데이터	10, 3.14, -25
String	문자열 데이터	'Hello', "World"
Boolean	논리 데이터 (참/거짓)	true, false
Null	값이 없음을 나타냄	null
Undefined	변수가 선언되었으나 값이 없음	let a; → a === undefined
*/

// 숫자 자료형
let number = 42;
console.log(number); // 출력: 42

// 문자열 자료형
let text = "Hello, Node.js!";
console.log(text); // 출력: Hello, Node.js!

// 불리언 자료형
let isActive = true;
console.log(isActive); // 출력: true

// null과 undefined
let emptyValue = null;
let uninitialized;
console.log(emptyValue); // 출력: null
console.log(uninitialized); // 출력: undefined

// 특이값
// Infinity: 무한대를 의미합니다.
console.log(1 / 0); // 출력: Infinity

// NaN (Not-a-Number): "Not-a-Number"의 약자로, 수학적으로 정의되지 않은 연산의 결과를 나타다
// 즉 연산을 수행할 수 없다는 의미
console.log("text" * 2); // 출력: NaN



/*
# 2. 객체 자료형과 메모리
객체 자료형은 참조(reference)를 통해 메모리를 관리한다.
데이터는 힙(heap) 메모리에 저장되며, 변수를 복사하면 동일한 메모리 주소를 참조한다.
*/

// 객체
let person = { name: "Alice", age: 25 };
console.log(person.name); // 출력: Alice

// 배열
let numbers = [1, 2, 3, 4];
console.log(numbers[2]); // 출력: 3

// 함수
let greet = function () {
  return "Hello!";
};
console.log(greet()); // 출력: Hello!


/*
# 3. typeof 연산자
typeof는 변수나 값의 자료형을 확인하는 데 사용된다.
*/

// typeof 예제
console.log(typeof 123); // 출력: "number"
console.log(typeof "JavaScript"); // 출력: "string"
console.log(typeof true); // 출력: "boolean"
console.log(typeof null); // 출력: "object" (JavaScript의 오래된 버그)
console.log(typeof undefined); // 출력: "undefined"
console.log(typeof { key: "value" }); // 출력: "object"
console.log(typeof [1, 2, 3]); // 출력: "object" (배열도 객체의 일종)
console.log(typeof function () {}); // 출력: "function"