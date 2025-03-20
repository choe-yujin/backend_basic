package mission.i_exception.exceptions;

// 입력 길이 초과 시 발생하는 예외
public class InputTooLongException extends Exception {
    public InputTooLongException(String message) {
        super(message);
    }
}
