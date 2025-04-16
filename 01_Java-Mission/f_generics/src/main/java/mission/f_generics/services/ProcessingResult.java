package mission.f_generics.services;

public class ProcessingResult<T> {
    private T fruit;
    private String result;

    public ProcessingResult(T fruit, String result) {
        this.fruit = fruit;
        this.result = result;
    }

    @Override
    public String toString() {
        return result;
    }
}

