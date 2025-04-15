package metaverse.chap01.section02;

import metaverse.chap01.section02.config.AppConfig;
import metaverse.chap01.section02.service.PaymentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * IoC(Inversion of Control, 제어의 역전)
 * 객체의 생성, 생명주기 관리까지 모든 객체에 대한 제어권이 바뀌었다는 것을 의미한다.
 * 즉, 개발자가 직접 객체를 생성하고 관리하는 대신, 프레임워크나 컨테이너가 객체의 생성,
 * 의존성 주입, 생명주기를 관리하는 방식으로 제어 흐름이 역전된 것을 말한다.
 * 예를 들어, 전통적인 방식에서는 개발자가 'new' 키워드로 객체를 직접 생성하지만, IoC에서는 컨테이너가 필요한 시점에 객체를 생성하고 제공한다.
 *
 * 1. IoC 컨테이너
 *    - 스프링에서 IoC를 담당하는 컨테이너를 의미한다.
 *    - Bean의 생성, 관리, 제거 등의 전체 라이프사이클을 관리한다.
 *    - 개발자가 직접 new 연산자로 객체를 생성하고 관리하는 것이 아닌 컨테이너가 대신 처리한다.
 *
 * 2. IoC의 장점
 *    - 객체 간의 결합도를 낮출 수 있다.
 *    - 유연한 코드 작성이 가능하다.
 *    - 코드의 재사용성이 높아진다.
 *    - 단위 테스트가 용이하다.
 *
 * 3. IoC의 구현 방법
 *    - DL(Dependency Lookup): 컨테이너가 제공하는 API로 Bean을 검색한다.
 *    - DI(Dependency Injection): 각 클래스 간의 의존관계를 자동으로 연결한다.
 *        * 생성자 주입 (Constructor Injection): 생성자를 통해 의존성을 주입한다.
 *        * 설정자 주입 (Setter Injection): setter 메소드를 통해 의존성을 주입한다.
 *        * 필드 주입 (Field Injection): 필드에 직접 의존성을 주입한다.
 *
 * 4. Bean의 Scope
 *    - 스프링 컨테이너가 관리하는 Bean의 생성 방식과 생성된 인스턴스의 관리 범위를 의미한다.
 *    - 싱글톤(Singleton): 기본 스코프로, 스프링 컨테이너당 하나의 인스턴스만 생성한다.
 *    - 프로토타입(Prototype): 요청할 때마다 새로운 인스턴스를 생성한다.
 *    - 웹 스코프: request, session, application 등이 있다.
 */
public class Application {
    public static void main(String[] args) {
        /*
         * IOC (Inversion of Control, 제어의 역전)
         * section01에서는 사용자가 직접 new 연산자를 사용하여 인스턴스를 생성하였다.
         * 그러나 이렇게 되면 우선 객체 간 결합도가 높아지고, 유연성이 떨어지며, 테스트와 유지보수가 어려워지는 문제가 발생한다.
         * 예를 들어, new로 직접 PaymentService를 생성하면 특정 구현체에 의존하게 되어 다른 결제 방식으로 변경하려면 코드를 수정해야 한다.
         * 이를 해결하기 위해 IoC를 적용하여 객체의 생성과 관리를 Spring 컨테이너에 위임한다.
         * 현재는 하나의 결제를 관리하기 때문에 크게 상관 없을 것 처럼 보이지만 아주 작은 프로젝트에서도 기본적으로 객체는 100개 이상이 생성된다.
         * 이러한 경우 new 연산자를 통해 직접 인스턴스를 생성하게 된다면 변경에 어려움이 점차 발생하게 될 것이다.
         *
         * ApplicationContext는 Spring의 IoC 컨테이너로, AppConfig.class 설정을 기반으로 Bean을 생성하고 관리한다.
         * context.getBean()을 통해 필요한 객체를 컨테이너에서 가져오며, 의존성 주입을 통해 결합도를 낮추고 유연한 설계를 가능하게 한다.
         */
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        PaymentService paymentService = context.getBean(PaymentService.class);

        String orderId = "order-123";
        double amount = 100.00;
        boolean result = paymentService.processPayment(orderId, amount);

        System.out.println("결제 결과 : " + (result ? "결제성공" : "결제 실패"));

        System.out.println();

        /*
        * spring IoC 컨테이너에서 동일한 Bean을 여러 번 요청할 경우, 기본적으로 싱글톤 스코프가 적용된다.
        * 싱글톤 스코프에서는 컨테이너가 동일한 Bean에 대해 단일 인스턴스만 생성하고 관리하기 때문에
        * context.getBean()으로 가져온 paymentService 인스턴스는 항상 동일한 객체를 참조한다.
        * */
        PaymentService paymentService2 = context.getBean(PaymentService.class);
        System.out.println("두 Bean은 같은가? " + (paymentService2 == paymentService)); // true 같은 주소

    }
}
