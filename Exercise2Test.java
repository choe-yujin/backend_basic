import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Exercise2Test {
    @Test
    void testPrintWeek() {
        Exercise2 application = new Exercise2();
        int[] input = {1, 3, 5, 8};
        String resultValue = application.printWeek(input);
        String expectedValue = "Monday, Wednesday, Friday, Unknown";
        Assertions.assertEquals(expectedValue, resultValue);
    }
}
