package mission.i_exception.exceptions;

// 입력이 비어있을 때 발생하는 예외
public class EmptyInputException extends Exception {
    public EmptyInputException(String message) { super(message); }
}