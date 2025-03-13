import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        int[] inputArr = getInputIntArray();
        String[] dayArr = decideDay(inputArr);
        System.out.println(printWeek(dayArr));
    }

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


    // 2. for문을 사용하여 배열의 각 요소를 순회한다.
    // 3. switch 문을 사용하여 각 숫자에 해당하는 요일 문자열을 결정한다.
    public static String[] decideDay(int[] inputArr) {
        String[] result = new String[inputArr.length];
        int idx = 0;

        // 2. for문을 사용하여 배열의 각 요소를 순회한다.
        for (int value : inputArr) {
            // 3. switch 문을 사용하여 각 숫자에 해당하는 요일 문자열을 결정한다.
            switch (value) {
                case 1:
                    result[idx] = "Monday";
                    break;
                case 2:
                    result[idx] = "Tuesday";
                    break;
                case 3:
                    result[idx] = "Wednesday";
                    break;
                case 4:
                    result[idx] = "Thursday";
                    break;
                case 5:
                    result[idx] = "Friday";
                    break;
                case 6:
                    result[idx] = "Saturday";
                    break;
                case 7:
                    result[idx] = "Sunday";
                    break;
                default:
                    result[idx] = "Unknown";
            }
            idx++;
        }

        return result;
    }

    // 4. 결정된 요일 문자열을 콤마로 연결하여 결과 문자열을 생성 후 반환한다.
    public static String printWeek(String[] result) {
        return String.join(", ", result);
    }

}
