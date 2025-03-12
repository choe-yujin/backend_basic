public class Exercise {
    public static void main(String[] args) {
        String text = "Java is Fun";
        String result = calculateLength(text);
        System.out.println(result);
    }

    public static String calculateLength(String text) {
        // 1. 입력 문자열을 split(" ")으로 분할하여 단어 배열을 생성한다.
        String[] texts = text.split(" ");
        //2. 배열의 인덱스 0, 1, 2의 단어를 변수에 직접 할당한다.
        String text1 = texts[0];
        String text2 = texts[1];
        String text3 = texts[2];

        //3. 각 단어의 길이를 length() 메서드로 계산한다.
        int length1 = text1.length();
        int length2 = text2.length();
        int length3 = text3.length();

        //4. 세 단어의 길이의 합계를 계산한다.
        int sum = length1 + length2 + length3;

        //5. 출력: "단어1: <길이1>, 단어2: <길이2>, 단어3: <길이3>, 총 길이: <합계>" 형태의 결과 문자열.
        return "단어1: " + length1 + ", 단어2: " + length2 + ", 단어3: " + length3 + ", 총 길이: " + sum;
    }
}

/*
미션 2: 입력 문자의 단어별 길이와 총 길이 구하기
입력: 공백으로 구분된 3개의 단어로 구성된 문자열 (예: "Java is Fun")
처리:
입력 문자열을 split(" ")으로 분할하여 단어 배열을 생성한다.
배열의 인덱스 0, 1, 2의 단어를 변수에 직접 할당한다.
각 단어의 길이를 length() 메서드로 계산한다.
세 단어의 길이의 합계를 계산한다.
출력: "단어1: <길이1>, 단어2: <길이2>, 단어3: <길이3>, 총 길이: <합계>" 형태의 결과 문자열.
제약: 반복문 없이, 변수, 데이터 타입, 연산자, 배열과 문자열 다루기 기술만 사용한다.*/
