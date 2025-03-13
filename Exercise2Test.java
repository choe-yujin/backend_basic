import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Exercise2Test {
    @Test
    @DisplayName("숫자에 따른 요일 StrArr 확인")
    void testDecideDay() {
        Exercise2 application = new Exercise2();
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8};

        String[] resultValue = application.decideDay(input);
        String[] expectedValue = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Unknown"};
        Assertions.assertArrayEquals(expectedValue, resultValue);
    }

    @Test
    @DisplayName("StrArr Convert print 확인")
    void testPrintWeek() {
        Exercise2 application = new Exercise2();
        String[] decidedStrArr = {"Monday", "Wednesday", "Friday", "Unknown"};

        String resultValue = application.printWeek(decidedStrArr);
        String expectedValue = "Monday, Wednesday, Friday, Unknown";
        Assertions.assertEquals(expectedValue, resultValue);
    }

}
