package mission.b_loop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise02Test {
    @Test
    void testGetDayFromNumber() {
        assertEquals("Monday", Exercise02.getDayFromNumber(1));
        assertEquals("Tuesday", Exercise02.getDayFromNumber(2));
        assertEquals("Wednesday", Exercise02.getDayFromNumber(3));
        assertEquals("Thursday", Exercise02.getDayFromNumber(4));
        assertEquals("Friday", Exercise02.getDayFromNumber(5));
        assertEquals("Saturday", Exercise02.getDayFromNumber(6));
        assertEquals("Sunday", Exercise02.getDayFromNumber(7));
        assertEquals("Unknown", Exercise02.getDayFromNumber(8));
    }

    @Test
    void testDecideDay() {
        int[] input = {1, 2, 3};
        String[] expected = {"Monday", "Tuesday", "Wednesday"};
        assertArrayEquals(expected, Exercise02.decideDay(input));
    }

    @Test
    void testPrintWeek() {
        String[] input = {"Monday", "Tuesday", "Wednesday"};
        assertEquals("Monday, Tuesday, Wednesday", Exercise02.printWeek(input));
    }
} 