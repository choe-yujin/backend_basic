package metaverse.valueobjct.chap01.section02;

import java.util.Objects;

public class GuestCount {
    private int value;

    protected GuestCount() {}

    public GuestCount(int value) {
        if(value <= 0) {
            throw new IllegalArgumentException("투숙객 수는 1명 이상이어야 합니다.");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GuestCount that = (GuestCount) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /*

불변 객체 형성에 기여하는 요소:
setter 메서드를 제공하지 않습니다.
객체 생성 후 내부 상태(value 필드)를 변경할 수 없도록 하여 불변성을 유지한다.
생성 시점에 유효성 검증을 수행한다.
생성될 때부터 유효한 상태를 가지도록 보장한다.
getValue() 메서드를 통해 내부 상태를 읽기만 할 수 있도록 제공한다.
외부에서 내부 상태를 간접적으로 변경할 수 있는 방법을 제공하지 않는다.
*
이러한 설계를 통해 GuestCount 객체는 생성된 후에는 그 상태가 변하지 않는 불변 객체의 특징을 가진다.
이는 값 객체의 중요한 특징 중 하나이며, 예기치 않은 부작용을 줄이고 코드를 더욱 안전하게 만든다.*/

}
