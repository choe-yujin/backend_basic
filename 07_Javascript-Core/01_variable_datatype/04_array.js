/*
2차원 배열: 미니 바둑판 예제
바둑판의 각 좌표를 관리하기 위해 2차원 배열을 사용한다. 기본적으로 빈 바둑판(0)을 생성하고, 특정 위치에 돌을 놓는 작업을 수행한다.

개발 순서:
빈 4x4 바둑판을 만든다.
각 행을 직접 정의하여 배열에 추가한다.
특정 좌표에 흑돌(1)과 백돌(2)을 놓는다.
특정 행과 열 데이터를 출력한다.
*/

// 1. 바둑판 초기화: 4x4의 빈 바둑판 생성
let board = [
    [0, 0, 0, 0],
    [0, 0, 0, 0],
    [0, 0, 0, 0],
    [0, 0, 0, 0]
];

console.log("빈 바둑판:\n", board[0]+"\n" + board[1]+"\n"+ board[2]+"\n"+ board[3]);

// 2. 특정 위치에 돌 놓기
// 흑돌(1)을 (1, 1) 위치에 놓는다.
board[1][1] = 1;
// 백돌(2)을 (2, 3) 위치에 놓는다.
board[2][3] = 2;

console.log("(1, 1) 위치에 흑돌:", board[1][1]); // 출력: 1
console.log("(2, 3) 위치에 백돌:", board[2][3]); // 출력: 2

// 3. 행과 열의 데이터 접근
// 1번째 행 전체 출력
console.log("1번째 행:", board[1]);

// 2번째 열 데이터 출력
let secondColumn = [board[0][1], board[1][1], board[2][1], board[3][1]];
console.log("2번째 열:", secondColumn);

/*
응용 문제:
4x4 바둑판을 초기화하고 (0, 0) 위치에 흑돌(1), (3, 3) 위치에 백돌(2)을 놓으시오.
2번째 행에 모든 칸을 흑돌로 채우시오.
*/

/*
다차원 배열의 개념
다차원 배열은 2차원 배열을 확장하여 여러 차원의 데이터를 관리할 수 있다. 예를 들어, 여러 개의 바둑판을 관리하는 경우 다차원 배열을 사용할 수 있다.
*/

// 다차원 배열: 2개의 4x4 바둑판 관리
let multiBoard = [
    [
        [0, 0, 0, 0],
        [0, 0, 0, 0],
        [0, 0, 0, 0],
        [0, 0, 0, 0]
    ],
    [
        [0, 0, 0, 0],
        [0, 0, 0, 0],
        [0, 0, 0, 0],
        [0, 0, 0, 0]
    ]
];

// 첫 번째 바둑판의 (1, 1) 위치에 흑돌 놓기
multiBoard[0][1][1] = 1;
console.log("첫 번째 바둑판 (1, 1) 위치에 흑돌:", multiBoard[0][1][1]); // 출력: 1

// 두 번째 바둑판의 (3, 3) 위치에 백돌 놓기
multiBoard[1][3][3] = 2;
console.log("두 번째 바둑판 (3, 3) 위치에 백돌:", multiBoard[1][3][3]); // 출력: 2
/*
자료형 변환과 요구사항 해결
학생 관리 시스템에서 문자열로 저장된 데이터(이름, 나이, 점수)를 처리해야 한다.
점수는 숫자로 변환해 평균을 계산한다.
나이는 숫자로 변환해 성인 여부를 판단한다.
*/

// 1. 문제 상황: 문자열 데이터의 연산 문제
let student1Name = "김철수";
let student1Age = "20";
let student1Score = "85";

let student2Name = "김동현";
let student2Age = "18";
let student2Score = "78";

let student3Name = "안태수";
let student3Age = "16";
let student3Score = "92";

// 점수 평균을 계산하려고 시도
let totalScore = student1Score + student2Score + student3Score; // 점수가 문자열로 연결됨
console.log("학생 평균 점수:", totalScore / 3); // 출력: NaN

/*
문제점:
문자열 형태의 데이터로 인해 연산이 제대로 수행되지 않음.
암묵적 변환과 명시적 변환을 활용해 문제를 해결한다.
*/

// 2. 암묵적 변환으로 문제 해결
// 단항 플러스 연산자 : 주로 변수를 숫자로 변환하는 데 사용
console.log("\n# 암묵적 형변환 사용");
totalScore = +student1Score + +student2Score + +student3Score; // 문자열이 숫자로 암묵적으로 변환
console.log("학생 평균 점수:", totalScore / 3); // 출력: 85

// 문자열 형식의 숫자 값에 - 0 연산을 이용해서 숫자로 형변환환
let isStudent1Adult = student1Age - 0 >= 18; // 문자열이 숫자로 변환되어 비교됨
let isStudent2Adult = student2Age - 0 >= 18;
let isStudent3Adult = student3Age - 0 >= 18;
//console.log(${student1Name} 성인 여부:, isStudent1Adult); // 출력: true
//console.log(${student2Name} 성인 여부:, isStudent2Adult); // 출력: true
//console.log(${student3Name} 성인 여부:, isStudent3Adult); // 출력: false

/*
장점:
코드를 간결하게 작성할 수 있다.
자동 변환으로 작업이 빠르게 진행된다.

문제점:
변환 과정이 명확하지 않아 코드 가독성이 떨어질 수 있다.
데이터가 예상치 못한 방식으로 처리될 위험이 있다.
*/

// 3. 명시적 변환으로 문제 해결
console.log("\n# 명시적 형변환 사용");
totalScore = Number(student1Score) + Number(student2Score) + Number(student3Score); // 명시적으로 문자열을 숫자로 변환
console.log("학생 평균 점수:", totalScore / 3); // 출력: 85

let student1AgeNumber = Number(student1Age); // 명시적으로 문자열을 숫자로 변환
let student2AgeNumber = Number(student2Age);
let student3AgeNumber = Number(student3Age);

isStudent1Adult = student1AgeNumber >= 18; // 숫자로 변환된 나이를 비교
isStudent2Adult = student2AgeNumber >= 18;
isStudent3Adult = student3AgeNumber >= 18;
//console.log(${student1Name} 성인 여부:, isStudent1Adult); // 출력: true
//console.log(${student2Name} 성인 여부:, isStudent2Adult); // 출력: true
//console.log(${student3Name} 성인 여부:, isStudent3Adult); // 출력: false

/*
장점:
변환 과정을 명확히 제어할 수 있다.
데이터 처리의 의도가 분명하여 유지보수성이 높아진다.
*/

// 4. 결론 및 요약
/*
암묵적 변환:
편리하지만 가독성이 떨어지고, 데이터 오류 가능성이 있다.
명시적 변환:
명확성과 데이터 일관성을 유지하며, 코드의 안정성이 높아진다.

실제 프로젝트에서는 명시적 변환을 사용하는 것이 권장된다.
*/