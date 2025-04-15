package metaverse.chap01.section03.config;

import metaverse.chap01.section03.service.KaKaoPayGateway;
import metaverse.chap01.section03.service.NaverPayGateway;
import metaverse.chap01.section03.service.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

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
    싱글톤 스코프 (Singleton Scope)
    Spring의 기본 스코프이며, 컨테이너 내에서 단일 인스턴스만 생성된다.
    모든 요청에서 동일한 객체를 반환하므로 메모리 효율적이고, 상태를 공유해야 하는 경우 유용하다.
    주의: 상태(예: 필드 값)를 가지면 모든 요청이 이를 공유하므로, 상태 비저장(stateless) 객체에 적합하다.
    사용 사례: 설정 객체, 서비스 로직(상태를 가지지 않는 경우).
    아래 'singlePay' 빈은 NaverPayGateway를 사용하며, 컨테이너가 종료될 때까지 동일한 인스턴스를 유지한다.
    */
    @Bean("singlePay") // bean의 이름으로 식별
    @Scope("singleton")
    public PaymentService paymentService() {
        return new PaymentService(naverPayGateway());
    }

    /*
    프로토타입 스코프 (Prototype Scope)
    요청할 때마다 새로운 인스턴스를 생성하며, 컨테이너는 생성 후 해당 객체를 관리하지 않는다.
    각 요청이 독립적인 상태를 유지해야 할 때 유용하며, 상태를 가지는 객체에 적합하다.
    주의: 빈이 생성된 후 Spring이 소멸 시점을 관리하지 않으므로, 메모리 해제를 별도로 고려해야 할 수 있다.
    사용 사례: 사용자별 주문 객체, 요청별로 독립적인 데이터가 필요한 경우.
    아래 'protoPay' 빈은 KakaoPayGateway를 사용하며, getBean() 호출 시마다 새로운 인스턴스가 생성된다.
    */
    @Bean("protoPay") // bean의 이름으로 식별
    @Scope("prototype")
    public PaymentService protoPayService() {
        return new PaymentService(naverPayGateway());
    }
}
