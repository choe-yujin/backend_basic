package mission.f_generics.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppleTest {
    private Apple apple;

    @BeforeEach
    void setUp() {
        apple = new Apple("사과", "빨간색", 5000.0);
        System.out.println("테스트를 위한 사과가 준비되었습니다.");
    }

    @Test
    @DisplayName("색깔에 따른 문자열 출력 확인 - 빨간색")
    void testRedAppleCut() {
        String expected = "익은 사과 껍질을 깎습니다.";
        String actual = apple.cut();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("색깔에 따른 문자열 출력 확인 - 녹색")
    void testGreenAppleCut() {
        apple.setColor("녹색");

        String expected = "덜 익은 사과 껍질을 깎습니다.";
        String actual = apple.cut();
        assertEquals(expected, actual);
    }

    @AfterEach
    void tearDown() {
        apple = null;
        System.out.println("테스트가 끝나 사과를 해제합니다.");
    }
}