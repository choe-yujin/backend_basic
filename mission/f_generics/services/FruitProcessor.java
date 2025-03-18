package mission.f_generics.services;

import mission.f_generics.interfaces.Peelable;

public class FruitProcessor<T extends Peelable> {
    private T fruit;

    public FruitProcessor(T fruit) {
        this.fruit = fruit;
    }

/*    처리기 클래스들이 ProcessingResult를 반환하도록 수정한다.
    과일의 상태(예: 바나나의 익은 정도, 사과의 색상)에 따라 다른 처리 결과를 반환하도록 구현한다.*/
    public ProcessingResult<T> process() {
        String result = fruit.peel(); // peel 메서드는
        return new ProcessingResult<>(fruit, result); // 과일 상태에 따라 다른 결과 반환
    }
}
