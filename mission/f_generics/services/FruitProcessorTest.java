package mission.f_generics.services;

import mission.f_generics.model.Banana;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FruitProcessorTest {
    private Banana banana;

    @BeforeEach
    void setUp() {
        banana = new Banana("바나나", 9, 5000.0);
        System.out.println("테스트를 위한 바나나가 준비되었습니다.");
    }

    @Test
    @DisplayName("processor는 ProcessingResult를 반환 - 바나나")
    void testProcessBanana() {
        FruitProcessor<Banana> processor = new FruitProcessor<>(banana);

        ProcessingResult<Banana> result = processor.process();
        assertEquals("많이 익은 바나나 껍질을 쉽게 벗깁니다", result.toString());
    }

    @AfterEach
    void tearDown() {
        banana = null;
        System.out.println("테스트가 끝나 바나나를 해제합니다.");
    }
}