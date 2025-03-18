package mission.loop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Exercise02Test {
    @Test
    @DisplayName("정수 배열을 요일 문자열 배열로 변환 - 정상 입력")
    void testDecideDay() {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8};

        String[] resultValue = Exercise02.decideDay(input);
        String[] expectedValue = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Unknown"};
        Assertions.assertArrayEquals(expectedValue, resultValue);
    }

    @Test
    @DisplayName("정수 배열이 빈 배열일 때 - 빈 배열 반환")
    void testDecideDayWithEmptyArray() {
        int[] input = {};

        String[] resultValue = Exercise02.decideDay(input);
        String[] expectedValue = {};

        Assertions.assertArrayEquals(expectedValue, resultValue);
    }

    @Test
    @DisplayName("요일 문자열 배열을 콤마로 연결된 문자열로 변환")
    void testPrintWeek() {
        String[] decidedStrArr = {"Monday", "Wednesday", "Friday", "Unknown"};

        String resultValue = Exercise02.printWeek(decidedStrArr);
        String expectedValue = "Monday, Wednesday, Friday, Unknown";

        Assertions.assertEquals(expectedValue, resultValue);
    }

    @Test
    @DisplayName("빈 요일 문자열 배열을 변환할 경우 - 빈 문자열 반환")
    void testPrintWeekWithEmptyArray() {
        String[] decidedStrArr = {};

        String resultValue = Exercise02.printWeek(decidedStrArr);
        String expectedValue = "";

        Assertions.assertEquals(expectedValue, resultValue);
    }

}
