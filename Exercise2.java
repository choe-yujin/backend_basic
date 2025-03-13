public class Exercise2 {
    public static void main(String[] args) {
        System.out.println(printWeek(new int[]{1, 3, 5, 8}));
    }

    // 1. 입력 정수 배열을 매개변수로 받는 메소드를 정의한다. {1,3,5,8}
    public static String printWeek(int[] input) {

        String[] result = new String[input.length];
        int idx = 0;

        // 2. for문을 사용하여 배열의 각 요소를 순회한다.
        for (int value : input) {
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

        // 4. 결정된 요일 문자열을 콤마로 연결하여 결과 문자열을 생성 후 반환한다.
        return String.join(", ", result);
    }
}