# 스프링 컨테이너와 의존성 주입(DI) 이해하기

## 1. 핵심 개념과 패턴

이 프로젝트에서 확인할 수 있는 핵심 개념과 패턴은 다음과 같습니다:

1. **의존성 주입(Dependency Injection, DI)**: 객체가 필요로 하는 다른 객체(의존성)를 직접 생성하지 않고 외부에서 주입받는 방식
2. **제어의 역전(Inversion of Control, IoC)**: 객체 생성과 생명주기 관리를 개발자가 아닌 프레임워크(스프링)가 담당하는 패턴
3. **스프링 컨테이너**: 빈(Bean)들을 생성하고 관리하는 스프링의 핵심 요소
4. **빈 스코프(Bean Scope)**: 빈의 생성 방식과 생존 범위를 정의하는 방식 (싱글톤, 프로토타입)
5. **빈 생명주기(Bean Lifecycle)**: 빈이 생성되고 소멸될 때까지의 과정과 그 단계마다 실행되는 메서드
6. **인터페이스 기반 설계**: 구현체가 아닌 인터페이스에 의존하여 유연성을 확보하는 패턴

## 2. 비유와 예시

### 의존성 주입과 IoC

**비유**: 의존성 주입은 마치 레스토랑에서의 경험과 비슷합니다.

1) **전통적인 방식 (section01)**: 직접 요리하기
    - 음식을 먹기 위해 직접 식재료를 구매하고 요리해야 합니다.
    - 요리법이 바뀌면 여러분이 직접 새로운 조리법을 배우고 적용해야 합니다.
    - `PaymentService`가 직접 `new KaKaoPayGateway()` 또는 `new NaverPayGateway()`를 생성하여 의존하는 방식입니다.

2) **스프링 방식 (section02~04)**: 레스토랑에서 식사하기
    - 음식(서비스)이 필요할 때 주문만 하면 요리사(스프링 컨테이너)가 알아서 준비해줍니다.
    - 메뉴가 바뀌어도(구현체가 변경되어도) 여러분은 같은 방식으로 주문만 하면 됩니다.
    - `PaymentService`는 자신이 사용할 결제 방식을 직접 생성하지 않고, 생성자를 통해 외부(스프링 컨테이너)에서 주입받습니다.

### 싱글톤과 프로토타입 스코프

**비유**: 학교의 공용 자원과 개인 자원으로 비유할 수 있습니다.

1) **싱글톤 스코프(section03의 singlePay)**: 학교의 공용 도서관
    - 모든 학생이 같은 도서관을 사용합니다.
    - 한 학생이 도서관에 책을 추가하면 모든 학생이 그 책을 볼 수 있습니다(상태 공유).
    - 모든 요청이 같은 객체 인스턴스를 공유합니다.

2) **프로토타입 스코프(section03의 protoPay)**: 학생 개인의 노트북
    - 각 학생은 자신만의 노트북을 가집니다.
    - 한 학생이 자신의 노트북에 파일을 저장해도 다른 학생의 노트북에는 영향이 없습니다(독립적인 상태).
    - 요청할 때마다 새로운 객체 인스턴스가 생성됩니다.

### Bean 생명주기

**비유**: Bean의 생명주기는 직원의 회사 생활과 비슷합니다.

1) **생성**: 회사에 채용되기
    - 직원(Bean)이 회사(컨테이너)에 합류합니다.
    - `PaymentService` 생성자가 호출됩니다.

2) **초기화(init)**: 업무 교육 및 준비
    - 직원이 업무에 필요한 교육을 받고 작업 환경을 설정합니다.
    - `init()` 메서드에서 필요한 초기 설정을 수행합니다.

3) **사용**: 실제 업무 수행
    - 교육을 마친 직원이 실제 업무를 수행합니다.
    - `processPayment()` 같은 비즈니스 메서드가 호출됩니다.

4) **소멸(destroy)**: 퇴사 및 인수인계
    - 직원이 퇴사할 때 자신의 업무를 정리하고 인수인계합니다.
    - `destroy()` 메서드에서 사용했던 자원을 정리합니다.

## 3. 왜 이런 방식으로 구현했는지 & 대안

### 전통적인 방식(section01)의 문제점

1. **강한 결합도**: `PaymentService`가 구체적인 구현체(`KaKaoPayGateway` 또는 `NaverPayGateway`)에 직접 의존하기 때문에 변경이 어렵습니다.
2. **코드 변경**: 결제 방식을 변경하려면 `PaymentService` 내부 코드를 직접 수정해야 합니다.
3. **테스트 어려움**: 결합도가 높아 단위 테스트가 어렵습니다.

### 개선된 방식(section02~04)의 장점

1. **느슨한 결합**: `PaymentService`는 추상화된 `PaymentInterface`에만 의존하여 구현체 변경이 용이합니다.
2. **외부 구성**: `AppConfig`를 통해 어떤 구현체를 사용할지 외부에서 결정합니다.
3. **테스트 용이성**: Mock 객체를 주입하여 단위 테스트가 가능합니다.

### 대안적 접근 방식

1. **애노테이션 기반 설정**:
    - 현재 코드는 자바 클래스(`AppConfig`)를 통한 명시적 구성을 사용합니다.
    - 대안으로 `@Component`, `@Autowired` 등의 애노테이션을 사용할 수 있습니다.
   ```java
   @Component
   public class NaverPayGateway implements PaymentInterface { ... }
   
   @Component
   public class PaymentService {
       @Autowired
       public PaymentService(PaymentInterface paymentGateway) { ... }
   }
   ```

2. **XML 기반 설정**:
    - 자바 클래스 대신 XML 파일로 Bean을 구성할 수 있습니다.
   ```xml
   <bean id="naverPayGateway" class="metaverse.service.NaverPayGateway" />
   <bean id="paymentService" class="metaverse.service.PaymentService">
       <constructor-arg ref="naverPayGateway" />
   </bean>
   ```

3. **스프링 부트 자동 구성**:
    - 스프링 부트를 사용하면 더 간결한 설정이 가능합니다.
    - 조건부 자동 구성을 통해 특정 조건에 따라 자동으로 Bean을 등록할 수 있습니다.

## 4. 실무 적용 시 장단점과 주의사항

### 장점

1. **유연성**: 구현체를 쉽게 변경할 수 있어 요구사항 변경에 빠르게 대응할 수 있습니다.
2. **테스트 용이성**: 의존성을 쉽게 모킹(mocking)할 수 있어 단위 테스트가 용이합니다.
3. **코드 관리**: 의존성 관계가 명확하게 드러나 코드 가독성과 유지보수성이 향상됩니다.
4. **재사용성**: 인터페이스 기반 설계로 컴포넌트 재사용이 용이합니다.

### 단점

1. **설정의 복잡성**: 대규모 애플리케이션에서는 설정 코드가 복잡해질 수 있습니다.
2. **디버깅 난이도**: 런타임에 객체가 생성되므로 초기 디버깅이 어려울 수 있습니다.
3. **학습 곡선**: 스프링의 DI와 IoC를 이해하는 데 초기 학습 시간이 필요합니다.
4. **오버엔지니어링**: 작은 프로젝트에서는 과도한 설계가 될 수 있습니다.

### 주의사항

1. **Bean 스코프 선택**:
    - 싱글톤은 메모리 효율적이지만 상태 공유에 주의해야 합니다.
    - 프로토타입은 매번 새 객체를 생성하므로 리소스 사용에 주의해야 합니다.

2. **순환 참조 방지**:
    - A가 B를 의존하고 B가 다시 A를 의존하는 순환 참조를 피해야 합니다.
    - 이는 애플리케이션 시작 실패나 예측 불가능한 동작을 유발할 수 있습니다.

3. **Bean 생명주기 관리**:
    - `init()`과 `destroy()` 메서드에서 예외 처리를 철저히 해야 합니다.
    - 자원 누수를 방지하기 위해 `destroy()` 메서드에서 자원을 올바르게 해제해야 합니다.

4. **테스트 설계**:
    - 통합 테스트와 단위 테스트를 명확히 구분하고 적절한 테스트 환경을 구성해야 합니다.

## 5. Follow-up 질문 제안

이 주제에 대해 더 깊이 이해할 수 있는 질문들은 다음과 같습니다:

1. **컴포넌트 스캔 방식과 자바 설정 방식의 차이점은 무엇인가요?**
    - 각 방식의 장단점과 언제 어떤 방식을 선택해야 하는지 알아볼 수 있습니다.

2. **스프링의 다양한 Bean 스코프(request, session, application)는 어떤 상황에서 사용하는 것이 좋을까요?**
    - 웹 환경에서의 다양한 스코프 활용법에 대해 배울 수 있습니다.

3. **`@PostConstruct`와 `@PreDestroy` 애노테이션은 `initMethod`와 `destroyMethod` 속성과 어떻게 다른가요?**
    - Bean 생명주기 관리의 여러 방법을 비교해볼 수 있습니다.

4. **스프링에서 프로토타입 빈이 싱글톤 빈을 의존할 때와 그 반대의 경우 어떤 문제가 발생할 수 있나요?**
    - 스코프 간의 상호작용과 주의사항에 대해 학습할 수 있습니다.

5. **스프링 부트의 자동 구성(Auto-configuration)은 기존의 스프링 컨테이너와 어떻게 다른가요?**
    - 최신 스프링 생태계의 발전 방향을 이해할 수 있습니다.


---
# 실무에서의 싱글톤과 프로토타입 스코프 활용

실무에서 싱글톤과 프로토타입 스코프의 선택은 애플리케이션의 요구사항과 특성에 따라 달라집니다. 각각의 적합한 사용 사례를 살펴보겠습니다.

## 싱글톤 스코프 사용 사례

1. **서비스 계층 (Service Layer)**
    - 비즈니스 로직을 담당하는 서비스 클래스는 대부분 상태를 갖지 않고 기능만 제공하므로 싱글톤으로 사용합니다.
    - 예: `PaymentService`, `UserService`, `OrderService` 등

2. **리포지토리 계층 (Repository Layer)**
    - 데이터 액세스 로직을 담당하는 Repository 클래스들은 보통 상태를 갖지 않으므로 싱글톤으로 사용합니다.
    - 예: `UserRepository`, `ProductRepository` 등

3. **설정 클래스 (Configuration)**
    - 애플리케이션 설정 정보를 담고 있는 클래스는 변경될 일이 거의 없으므로 싱글톤으로 사용합니다.
    - 예: `DatabaseConfig`, `SecurityConfig` 등

4. **유틸리티 클래스 (Utility)**
    - 공통 기능을 제공하는 유틸리티 클래스는 상태가 없으므로 싱글톤으로 사용합니다.
    - 예: `StringUtils`, `DateUtils` 등

5. **캐시 서비스 (Cache Service)**
    - 애플리케이션 전체에서 공유해야 하는 캐시 데이터를 관리하는 서비스는 싱글톤이 적합합니다.
    - 예: `ProductCacheService`, `UserCacheService` 등

## 프로토타입 스코프 사용 사례

1. **사용자별 상태 관리 객체**
    - 각 사용자마다 독립적인 상태를 유지해야 하는 객체는 프로토타입이 적합합니다.
    - 예: `ShoppingCart`, `UserSession` 등
    - 실제 예시: 웹 애플리케이션에서 여러 사용자가 동시에 각자의 장바구니를 사용할 때

2. **요청별 처리 객체**
    - 각 요청마다 다른 상태나 설정이 필요한 경우 프로토타입을 사용합니다.
    - 예: `RequestValidator`, `PaymentProcessor` 등
    - 실제 예시: 다양한 결제 요청마다 다른 유효성 검사 규칙이 필요할 때

3. **비용이 큰 연산을 수행하는 객체**
    - 많은 자원을 사용하지만 공유할 수 없는 무거운 객체는 필요할 때만 생성하는 프로토타입이 적합합니다.
    - 예: `ReportGenerator`, `DataAnalyzer` 등
    - 실제 예시: 특정 조건에서만 사용되는 복잡한 보고서 생성기

4. **멀티스레드 환경에서의 상태 객체**
    - 스레드 간 상태 공유가 위험한 객체는 프로토타입으로 만들어 각 스레드마다 독립된 인스턴스를 사용합니다.
    - 예: `TransactionContext`, `UserContext` 등
    - 실제 예시: 여러 스레드가 동시에 작업할 때 각 스레드별로 독립적인 트랜잭션 처리

5. **임시 데이터 홀더**
    - 특정 작업에만 사용되고 버려지는 임시 데이터 객체는 프로토타입이 적합합니다.
    - 예: `ImportData`, `ExportConfiguration` 등
    - 실제 예시: 대량 데이터 가져오기/내보내기 작업에서 각 작업마다 고유한 설정이 필요할 때

## 실무 사례 연구

### 싱글톤 실제 사례: 결제 게이트웨이 연동 서비스

```java
@Service  // 스프링의 기본 스코프는 싱글톤
public class PaymentGatewayService {
    private final String apiKey;
    private final RestTemplate restTemplate;
    
    public PaymentGatewayService(@Value("${payment.api.key}") String apiKey) {
        this.apiKey = apiKey;
        this.restTemplate = new RestTemplate();
    }
    
    public PaymentResult processPayment(PaymentRequest request) {
        // 상태를 저장하지 않고 매개변수와 API 키만 사용하여 결제 처리
        // 모든 요청이 동일한 인스턴스를 공유해도 안전함
        return restTemplate.postForObject("/payment", request, PaymentResult.class);
    }
}
```

**적합한 이유**:
- API 키와 RestTemplate은 한 번 초기화된 후 변경되지 않는 불변 상태입니다.
- 모든 결제 요청은 독립적인 매개변수로 처리되므로 인스턴스 상태에 의존하지 않습니다.
- 매번 새로운 객체를 생성하는 것보다 하나의 인스턴스를 재사용하는 것이 메모리 효율적입니다.

### 프로토타입 실제 사례: 주문 처리 상태 관리

```java
@Component
@Scope("prototype")  // 명시적으로 프로토타입 스코프 지정
public class OrderProcessor {
    private Order currentOrder;
    private List<PaymentAttempt> paymentAttempts = new ArrayList<>();
    private ShippingStatus shippingStatus;
    
    public void setOrder(Order order) {
        this.currentOrder = order;
    }
    
    public void processPayment(PaymentMethod method) {
        PaymentAttempt attempt = new PaymentAttempt(method, currentOrder.getAmount());
        paymentAttempts.add(attempt);
        // 결제 처리 로직...
    }
    
    public List<PaymentAttempt> getPaymentHistory() {
        return paymentAttempts;
    }
    
    public void updateShippingStatus(ShippingStatus status) {
        this.shippingStatus = status;
    }
}
```

**사용 코드**:
```java
@Service
public class OrderService {
    @Autowired
    private ApplicationContext context;
    
    public void processNewOrder(Order order) {
        // 각 주문마다 새로운 OrderProcessor 인스턴스 획득
        OrderProcessor processor = context.getBean(OrderProcessor.class);
        processor.setOrder(order);
        processor.processPayment(order.getPaymentMethod());
        // 추가 처리...
    }
}
```

**적합한 이유**:
- 각 주문은 독립적인 상태(결제 시도 이력, 배송 상태 등)를 가지므로 별도의 인스턴스가 필요합니다.
- 여러 주문이 동시에 처리될 때 상태가 섞이면 심각한 문제가 발생할 수 있습니다.
- 주문 처리가 완료된 후에는 해당 인스턴스가 더 이상 필요 없으므로 프로토타입이 적합합니다.

## 실무 적용 시 주의사항

### 싱글톤 사용 시 주의사항

1. **상태 공유 문제**:
    - 필드에 상태를 저장하면 모든 요청 간에 공유되므로 동시성 문제가 발생할 수 있습니다.
    - 해결책: 상태가 없는(stateless) 설계를 하거나, 필요한 경우 ThreadLocal 사용

2. **초기화 비용**:
    - 싱글톤은 애플리케이션 시작 시 로드되므로 초기화가 무거우면 시작 시간이 길어집니다.
    - 해결책: 지연 로딩(lazy loading) 또는 비동기 초기화 고려

### 프로토타입 사용 시 주의사항

1. **생명주기 관리**:
    - 스프링은 프로토타입 Bean을 생성만 하고 소멸 메서드(`@PreDestroy`)를 호출하지 않습니다.
    - 해결책: 자원 해제가 필요한 경우 명시적으로 정리 메서드를 호출하거나 커스텀 스코프 구현

2. **성능 영향**:
    - 빈번한 객체 생성은 GC 부담을 증가시키고 성능에 영향을 줄 수 있습니다.
    - 해결책: 객체 풀링(pooling) 고려 또는 필요한 경우에만 프로토타입 사용

3. **싱글톤-프로토타입 의존성 문제**:
    - 싱글톤 Bean이 프로토타입 빈을 의존하면 프로토타입의 목적이 무효화될 수 있습니다.
    - 해결책: `ObjectFactory`, `Provider` 또는 스코프 프록시(Scoped Proxy) 사용

## 결론

실무에서는 대부분의 경우 싱글톤 스코프가 기본적으로 사용됩니다. 스프링의 기본 스코프가 싱글톤인 이유도 이러한 현실적인 필요성 때문입니다. 그러나 상태 관리가 중요하고 각 요청마다 독립적인 상태가 필요한 경우에는 프로토타입 스코프를 적절히 활용하여 애플리케이션의 안정성과 유연성을 높일 수 있습니다.

특히 프로토타입은 필요할 때만 선택적으로 사용하고, 빈의 생명주기와 자원 관리에 특별히 주의를 기울이는 것이 좋습니다.