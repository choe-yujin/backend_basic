package metaverse.chap01.section04;

import metaverse.chap01.section04.config.AppConfig;
import metaverse.chap01.section04.service.PaymentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        /*
        Bean의 생명주기를 이해해야 하는 이유:
        리소스 관리 최적화:
        Bean의 생성, 초기화, 소멸 단계를 알면 리소스(예: DB 연결, 파일 핸들)를 적절히 할당하고 해제할 수 있다.
        예: 초기화에서 DB 연결을 설정하고, 소멸에서 해제하지 않으면 리소스 누수가 발생할 수 있다.
        애플리케이션 동작 예측:
        생명주기 단계를 이해하면 Bean이 언제 생성되고, 언제 사용할 준비가 되며, 언제 소멸되는지 예측 가능.
        예: 결제 서비스가 초기화되지 않은 상태에서 사용되면 오류가 발생할 수 있다.
        커스터마이징 가능성:
        특정 시점에 로직을 추가할 수 있다.
        예: 결제 게이트웨이 인증을 초기화 시점에 수행하거나, 소멸 시점에 로그를 정리.
        디버깅과 유지보수:
        생명주기 문제를 파악하면 초기화 실패나 리소스 해제 누락 같은 버그를 빠르게 진단 가능.
        예: 소멸 단계가 호출되지 않으면 메모리 누수가 발생할 수 있음을 알 수 있다.
        Spring 프레임워크 활용 극대화:
        Spring의 IoC 컨테이너가 Bean을 관리하는 방식을 이해하면, 의존성 주입과 생명주기 이벤트를 효과적으로 활용 가능.
        예: 싱글톤 빈의 초기화 시점을 조정해 애플리케이션 시작 속도를 최적화.
        결론: Bean 생명주기를 이해하면 애플리케이션의 안정성, 성능, 유지보수성을 높일 수 있다.
        */
        System.out.println("=== 빈의 생명주기 ===");
        PaymentService paymentService = context.getBean("paymentService", PaymentService.class);
        paymentService.processPayment("lifecycle 100", 100.0);

        ((AnnotationConfigApplicationContext) context).close();
    }
}
