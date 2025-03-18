package mission.f_generics.services;

import mission.f_generics.model.Apple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FruitCutterTest {
    private Apple apple;

    @BeforeEach
    void setUp() {
        apple = new Apple("사과", "빨간색", 5000.0);
        System.out.println("테스트를 위한 사과가 준비되었습니다.");
    }
    @Test
    @DisplayName("cutter는 ProcessingResult를 반환 - 사과")
    void testCut() {
        FruitCutter<Apple> cutter = new FruitCutter<>(apple);

        ProcessingResult<Apple> result = cutter.cut();
        assertEquals("익은 사과 껍질을 깎습니다.", result.toString());
    }

    @AfterEach
    void tearDown() {
        apple = null;
        System.out.println("테스트가 끝나 사과를 해제합니다.");
    }
}