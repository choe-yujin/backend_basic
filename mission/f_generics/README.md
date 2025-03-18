## <요구사항 명세화>  
### **미션 6: 과일 처리 시스템 구현 (제네릭스와 인터페이스 활용)**
- **입력:**
    - 과일 객체 배열과 처리 방식
- **처리:**
    1. 과일 처리 인터페이스들을 정의한다:
        ```java
        public interface Peelable {
            String peel();  // 손으로 벗기는 방식
        }
        
        public interface Cuttable {
            String cut();   // 칼로 깎는 방식
        }
        ```
    2. 과일 클래스들을 정의한다:
        - `Banana` 클래스 (Peelable 구현)
            - private 멤버 변수: name(String), ripeness(int), price(double)
            - peel() 메서드: "바나나 껍질을 벗깁니다" 반환
        - `Apple` 클래스 (Cuttable 구현)
            - private 멤버 변수: name(String), color(String), price(double)
            - cut() 메서드: "사과 껍질을 깎습니다" 반환
    3. 과일 처리기 클래스를 정의한다:
        ```java
        public class FruitProcessor<T extends Peelable> {
            private T fruit;
            
            public FruitProcessor(T fruit) {
                this.fruit = fruit;
            }
            
            public String process() {
                return fruit.peel();
            }
        }
        
        public class FruitCutter<T extends Cuttable> {
            private T fruit;
            
            public FruitCutter(T fruit) {
                this.fruit = fruit;
            }
            
            public String process() {
                return fruit.cut();
            }
        }
        ```
- **출력:**
    - 각 과일의 처리 결과 문자열
- **포함 기술:**  
  제네릭스, 인터페이스, 상속, 다형성, 클래스 및 객체
- **JUnit 활용:**  
  @BeforeEach, @AfterEach, @Test, assertEquals, assertTrue

<개발 순서>
1. `Peelable`과 `Cuttable` 인터페이스를 정의한다.
2. `Banana`와 `Apple` 클래스를 구현한다.
3. `FruitProcessor`와 `FruitCutter` 제네릭 클래스를 구현한다.
4. main() 메서드를 작성하여 각 과일의 처리 결과를 출력한다.

<예시 코드>
```java
// 메인 클래스
public class FruitMain {
    public static void main(String[] args) {
        Banana banana = new Banana("바나나", 8, 3000.0);
        Apple apple = new Apple("사과", "빨간색", 5000.0);
        
        FruitProcessor<Banana> processor = new FruitProcessor<>(banana);
        FruitCutter<Apple> cutter = new FruitCutter<>(apple);
        
        System.out.println(processor.process());  // "바나나 껍질을 벗깁니다"
        System.out.println(cutter.process());     // "사과 껍질을 깎습니다"
    }
}
```

<추가 요구사항>
1. 과일 처리 결과를 저장할 수 있는 `ProcessingResult` 클래스를 구현한다:
   ```java
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
   ```
2. 처리기 클래스들이 `ProcessingResult`를 반환하도록 수정한다.
3. 과일의 상태(예: 바나나의 익은 정도, 사과의 색상)에 따라 다른 처리 결과를 반환하도록 구현한다.
