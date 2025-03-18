package mission.f_generics.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BananaTest {
    private Banana banana;

    @BeforeEach
    void setUp() {
        banana = new Banana("바나나", 9, 5000.0);
        System.out.println("테스트를 위한 바나나가 준비되었습니다.");
    }

    @Test
    @DisplayName("익힘 정도에 따른 문자열 출력 확인 - 많이 익음")
    void testPeelOverRipeBanana() {
        assertTrue(banana.peel().contains("많이 익은"));
        assertTrue(banana.peel().contains("쉽게"));
    }

    @Test
    @DisplayName("익힘 정도에 따른 문자열 출력 확인 - 덜 익음")
    void testPeelunderRipeBanana() {
        banana.setRipeness(2);
        assertTrue(banana.peel().contains("덜 익은"));
        assertTrue(banana.peel().contains("조심히"));
    }

    @AfterEach
    void tearDown() {
        banana = null;
        System.out.println("테스트가 끝나 바나나를 해제합니다.");
    }
}