package mission.b_loop;

import java.util.Scanner;

public class Exercise02 {
    public static void main(String[] args) {
        int[] inputArr = getInputIntArray();
        String[] dayArr = decideDay(inputArr);
        System.out.println(printWeek(dayArr));
    }

    /**
     * 사용자로부터 정수 배열을 입력받는 메서드
     * @return 입력된 정수 배열
     */
    // 1. 입력 정수 배열을 매개변수로 받는 메소드를 정의한다. {1,3,5,8}
    public static int[] getInputIntArray() {
        System.out.println("배열의 크기를 입력해주세요: ");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt(); // 배열 크기 입력받기

        int[] inputArr = new int[size]; // 해당 크기의 배열 생성
        System.out.println("배열의 요소들을 입력해주세요: ");
        // 배열의 각 요소를 입력받아 저장
        for (int i = 0; i < size; i++) {
            inputArr[i] = sc.nextInt(); // 각 요소 입력받기
        }

        return inputArr; // 배열 반환
    }

    /**
     * 입력된 정수 배열을 요일 문자열 배열로 변환하는 메서드
     * @param inputArr : 변환할 정수 배열
     * @return 변환된 요일 문자열 배열
     */
    // 2. for문을 사용하여 배열의 각 요소를 순회한다.
    public static String[] decideDay(int[] inputArr) {
        int inputArrLength = inputArr.length;

        String[] result = new String[inputArrLength];

        for (int i = 0; i < inputArrLength; i++) {
            result[i] = getDayFromNumber(inputArr[i]); // 요일 변환
        }

        return result;
    }

    /**
     * 입력된 숫자에 해당하는 요일을 변환하는 메서드
     * @param number : 요일을 나타내는 숫자 1~7
     * @return : 해당 숫자에 대응하는 요일 문자열(잘못된 숫자는 Unknown)
     */
    // 3. switch 문을 사용하여 각 숫자에 해당하는 요일 문자열을 결정한다.
    public static String getDayFromNumber(int number) {
        return switch (number) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            case 7 -> "Sunday";
            default -> "Unknown";
        };
    }

    /**
     * 요일 문자열 배열을 콤마로 구분된 문자열로 변환하는 메서드
     * @param result : 변환할 요일 문자열 배열
     * @return 콤마로 연결된 요일 문자열
     */
    // 4. 결정된 요일 문자열을 콤마로 연결하여 결과 문자열을 생성 후 반환한다.
    public static String printWeek(String[] result) {
        return String.join(", ", result);
    }
} 