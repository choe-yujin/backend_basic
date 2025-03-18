package mission.f_generics.services;

import mission.f_generics.interfaces.Cuttable;

public class FruitCutter<T extends Cuttable> {
    private T fruit;
    public FruitCutter(T fruit) {
        this.fruit = fruit;
    }

    /*    처리기 클래스들이 ProcessingResult를 반환하도록 수정한다.
    과일의 상태(예: 바나나의 익은 정도, 사과의 색상)에 따라 다른 처리 결과를 반환하도록 구현한다.*/
    public ProcessingResult<T> cut() {
        String result = fruit.cut(); // peel 메서드는
        return new ProcessingResult<>(fruit, result); // 과일 상태에 따라 다른 결과 반환
    }
}
