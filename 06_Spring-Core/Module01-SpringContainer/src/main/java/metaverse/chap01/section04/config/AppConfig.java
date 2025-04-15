package metaverse.chap01.section04.config;

import metaverse.chap01.section04.service.KaKaoPayGateway;
import metaverse.chap01.section04.service.NaverPayGateway;
import metaverse.chap01.section04.service.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public KaKaoPayGateway kaKaoPayGateway() {
        return new KaKaoPayGateway();
    }

    @Bean
    public NaverPayGateway naverPayGateway() {
        return new NaverPayGateway();
    }


    /*
    Spring Bean 생명주기 (Bean Lifecycle)
    Spring IoC 컨테이너가 Bean을 생성하고 관리하는 일련의 과정을 의미한다.
    주요 단계:
    인스턴스 생성: 컨테이너가 PaymentService 객체를 생성 (new 호출).
    의존성 주입: 생성자를 통해 PaymentInterface (naverPayGateway)가 주입됨.
    초기화: @Bean의 initMethod="init"로 지정된 메서드가 호출되어 초기 설정 수행.
    예: 리소스 초기화 (DB 연결, 캐시 로드 등).
    사용: 애플리케이션에서 Bean을 사용 (예: processPayment 호출).
    소멸: @Bean의 destroyMethod="destroy"로 지정된 메서드가 호출되어 리소스 정리.
    예: 열린 연결 해제, 메모리 정리.
    *
    @Bean(initMethod, destroyMethod)의 역할:
    Spring이 기본적으로 제공하는 @PostConstruct, @PreDestroy 대신 사용자 정의 초기화/소멸 메서드를 지정.
    initMethod: Bean이 생성되고 의존성 주입 후 호출될 메서드를 정의.
    destroyMethod: 컨테이너 종료 시 Bean 소멸 직전에 호출될 메서드를 정의.
    *
    이점:
    생명주기 단계를 명시적으로 제어하여 리소스 관리와 애플리케이션 동작을 최적화.
    예: 결제 서비스에서 초기화 시 결제 게이트웨이 인증, 소멸 시 세션 종료.
    주의: initMethod와 destroyMethod는 public이고 매개변수가 없어야 하며, 예외 처리를 잘 설계해야 한다.
    */
    @Bean(initMethod = "init", destroyMethod = "destroy") // 서비스클래스에 정의해둔 init메서드와 destroy메서드가 있어야 이 어노테이션과 연결됨
    public PaymentService paymentService() {
        return new PaymentService(naverPayGateway());
    }
    // init - bean 생성 이후 초기 설정 메서드
    // destory는 보통 싱글톤보다는 프로토타입에서 정의해주는 편
}
