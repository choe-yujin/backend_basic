/*
객체 자료형과 메모리
객체 자료형은 참조(reference)를 통해 메모리를 관리한다.
데이터는 힙(heap) 메모리에 저장되며, 변수를 복사하면 동일한 메모리 주소를 참조한다.
/

// 학생을 관리하는 프로그램을 만들어야 하는 상황 발생.

let student1Name = "김철수";
let student1Age = 20;
let student1Score = 85;

let student2Name = "김동현";
let student2Age = 22;
let student2Score = 90;

let student3Name = "안태수";
let student3Age = 19;
let student3Score = 78;

console.log("학생 1:", student1Name, student1Age, student1Score); 
console.log("학생 2:", student2Name, student2Age, student2Score); 
console.log("학생 3:", student3Name, student3Age, student3Score); 
/
문제점:
학생이 추가되면 변수 선언과 관리가 복잡해진다.
변수 간 연관성을 유지하기 어렵다.

만약 2명의 학생을 추가해야 한다면?
/

/
배열
배열은 순서가 있는 데이터의 집합이다. 학생들의 이름, 나이, 점수를 각각의 배열로 관리하여 데이터를 효율적으로 저장할 수 있다.
배열의 표현식: 키워드 변수명 = [값0,값1]
위의 표현식에서 만약 값1에 정보를 확인하고 싶다면 다음과 같이 "변수명[1]"을 사용할 수 있다. 
이렇게 배열이 가지고 있는 순서를 인덱스라고 한다.
/

let studentNames = ["김철수", "김동현", "안태수"];
let studentAges = [20, 22, 19];
let studentScores = [85, 90, 78];

// 특정 학생 정보 출력
console.log("학생 1:", studentNames[0], studentAges[0], studentScores[0]); // 출력: Alice 20 85
console.log("학생 2:", studentNames[1], studentAges[1], studentScores[1]); // 출력: Bob 22 90
console.log("학생 3:", studentNames[2], studentAges[2], studentScores[2]); // 출력: Charlie 19 78

/
장점
같은 속성을 가진 데이터를 묶어서 관리할 수 있어 변수를 효율적으로 사용할 수 있게된다.

문제점
이름, 나이, 점수가 서로 다른 배열에 저장되어 있어 데이터 간의 연관성 부족.
특정 학생의 데이터를 수정할 때, 각 배열의 동일한 인덱스를 찾아야 한다.

만약 내가 관리하는 학생이 100명인 경우 어떤 인덱스가 누구의 점수인지 알 수 없다.
/



/
객체
객체는 키-값 쌍으로 데이터를 저장한다. 각 학생의 이름, 나이, 점수를 하나의 객체에 저장하면 데이터 간 연관성이 높아진다.
객체의 표현식: 키워드 변수명 = { 키1: 값1, 키2: 값2 }
위의 표현식에서 만약 키2에 해당하는 정보를 확인하고 싶다면 다음과 같이 "변수명.키2" 또는 "변수명['키2']"를 사용할 수 있다.
이렇게 객체의 키는 속성명이라고 한다.
/

/
객체로 1명의 학생 정보 관리
/

let student1 = { name: "김철수", age: 20, score: 85 };
let student2 = { name: "김동현", age: 22, score: 90 };
let student3 = { name: "안태수", age: 19, score: 78 };

console.log("학생 1:", student1.name, student1.age, student1.score); // 출력: Alice 20 85
console.log("학생 2:", student2.name, student2.age, student2.score); // 출력: Bob 22 90
console.log("학생 3:", student3.name, student3.age, student3.score); // 출력: Charlie 19 78

/
장점 :
데이터를 구조화하여 이름, 나이, 점수를 한곳에 저장 가능
키를 통해 직관적으로 데이터 접근
/


/
객체 배열
배열과 객체를 조합하여 학생 정보를 효율적으로 관리할 수 있다. 배열은 학생 목록을 관리하고, 객체는 각 학생의 데이터를 저장한다.
/

/
배열에 객체를 할당하여 3명의 학생 관리
/

let students = [
    { name: "Alice", age: 20, score: 85 },
    { name: "Bob", age: 22, score: 90 },
    { name: "Charlie", age: 19, score: 78 }
];

console.log("학생1 : ", students[0].name, students[0].age, students[0].score)
console.log("학생2 : ", students[1].name, students[1].age, students[1].score)
console.log("학생3 : ", students[2].name, students[2].age, students[2].score)


// 특정 학생 점수 수정
students[0].score = 95; // Alice의 점수 수정
console.log("수정된 학생 정보:", students[0]); // 출력: { name: 'Alice', age: 20, score: 95 }

/
장점:
배열을 통해 학생 목록을 관리.
각 학생 데이터는 객체로 저장되어 구조적 관리가 가능.
/


// 새로운 요구사항 학생의 취미를 관리도 관리하기

/
객체의 속성에 배열 추가
각 학생의 정보를 객체로 저장하고, 취미를 배열로 포함하여 관리한다.
/

/
객체에 배열을 포함하여 학생의 취미 관리
/

let students_hobby = [
    { name: "Alice", age: 20, score: 85, hobbies: ["Reading", "Gaming"] },
    { name: "Bob", age: 22, score: 90, hobbies: ["Cycling", "Swimming"] },
    { name: "Charlie", age: 19, score: 78, hobbies: ["Painting", "Dancing"] }
];


console.log("학생1 : ", students_hobby[0].name, students_hobby[0].age, students_hobby[0].score, students_hobby[0].hobbies[0])
console.log("학생1 : ", students_hobby[1].name, students_hobby[1].age, students_hobby[1].score, students_hobby[1].hobbies[1])
console.log("학생1 : ", students_hobby[2].name, students_hobby[2].age, students_hobby[2].score, students_hobby[2].hobbies)



/
장점:

객체와 배열을 결합하여 복잡한 데이터 구조를 관리 가능.
학생의 정보를 확장(예: 취미 추가)해도 기존 데이터 구조에 영향을 미치지 않음.
*/