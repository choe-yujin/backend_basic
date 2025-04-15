package mission.i_exception;

import mission.i_exception.exceptions.EmptyInputException;
import mission.i_exception.exceptions.InputTooLongException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
 * 1. junit과 예외 테스트
 * - Junit은 java에서 단위 테스트를 작성하고 실행하기 위한 프레임워크이다.
 * - 예외 테스트는 특정 코드 블록에서 예외가 발생하는지를 확인하는 테스트이다.
 *
 * 2. assertThrows 메서드
 *   - assertThrows는 지정한 예외가 발생하는지 검증하는 메서드이다.
 *   - 이 메서드는 예외가 발생하면 해당 예외 객체를 반환하며, 발생하지 않으면 테스트가 실패한다.
 *
 * */
public class ApplicationTests {
    private final int maxLength = 20;

    // 테스트용 입력 검증 메서드
    private void validateInput(String input, int maxLength) throws EmptyInputException, InputTooLongException {
        if (input == null || input.trim().isEmpty()) {
            throw new EmptyInputException("입력이 비어있습니다.");
        }

        if (input.length() > maxLength) {
            throw new InputTooLongException("입력이 너무 깁니다. 최대 " + maxLength + "자까지 입력 가능합니다.");
        }
    }


    @Test
    void logTransactionEmptyInputTest() {
        // null 입력 테스트
        EmptyInputException exception = Assertions.assertThrows(EmptyInputException.class, () -> {
            validateInput(null, maxLength);
        });

        Assertions.assertEquals("입력이 비어있습니다.", exception.getMessage());

        // 빈 문자열 입력 테스트
        Assertions.assertThrows(EmptyInputException.class, () -> {
            validateInput("", maxLength);
        });

        // 공백만 있는 문자열 입력 테스트
        Assertions.assertThrows(EmptyInputException.class, () -> {
            validateInput("   ", maxLength);
        });
    }

    @Test
    void logTransactionInputTooLongTest() {
        // 최대 길이보다 1자 더 긴 입력 테스트
        String tooLongInput = "a".repeat(maxLength + 1);
        InputTooLongException exception = Assertions.assertThrows(InputTooLongException.class, () -> {
            validateInput(tooLongInput, maxLength);
        });

        Assertions.assertEquals("입력이 너무 깁니다. 최대 " + maxLength + "자까지 입력 가능합니다.", exception.getMessage());

        // 매우 긴 입력 테스트
        String veryLongInput = "a".repeat(maxLength * 2);
        Assertions.assertThrows(InputTooLongException.class, () -> {
            validateInput(veryLongInput, maxLength);
        });
    }

    @Test
    void validInputTest() {
        // 정상적인 입력은 예외가 발생하지 않아야 함
        try {
            // 최대 길이보다 짧은 입력
            validateInput("유효한 입력", maxLength);

            // 최대 길이에 딱 맞는 입력
            validateInput("a".repeat(maxLength), maxLength);
        } catch (Exception e) {
            Assertions.fail("유효한 입력에 예외가 발생하면 안됩니다: " + e.getMessage());
        }
    }

}