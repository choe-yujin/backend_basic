package com.metaverse.injection.chap01.section02;

import com.metaverse.injection.chap01.section02.config.AppConfig;
import com.metaverse.injection.chap01.section02.service.PaymentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
의존성 주입 (Dependency Injection, DI)
Spring 프레임워크에서 객체 간 의존성을 개발자가 직접 관리하지 않고, IoC 컨테이너가 자동으로 주입하는 메커니즘.
동작 방식:
컴포넌트 스캔(@ComponentScan)으로 Bean 등록: @Component, @Service 등이 붙은 클래스를 탐지.
의존성 탐색: @Autowired 또는 생성자 주입으로 필요한 Bean을 찾아 연결.
주입 완료: PaymentService에 PaymentInterface 구현체가 자동으로 주입됨.
*
이점:
결합도 감소: 객체가 의존성을 직접 생성하지 않으므로 코드 유연성 증가.
예: PaymentService가 KakaoPayGateway를 직접 new 하지 않고 주입받음.
테스트 용이성: Mock 객체로 쉽게 대체 가능.
예: 단위 테스트에서 가짜 결제 게이트웨이를 주입.
유지보수성 향상: 의존성 변경 시 코드 수정 최소화.
예: NaverPayGateway에서 KakaoPayGateway로 변경해도 PaymentService 수정 불필요.
*
주의: 동일 타입의 Bean 충돌 가능성
현재 PaymentInterface를 구현한 KakaoPayGateway와 NaverPayGateway가 모두 @Component로 등록된 경우:
Spring이 PaymentService에 주입할 Bean을 결정하지 못해 NoUniqueBeanDefinitionException 발생.
예: PaymentService 생성 시 "어떤 PaymentInterface를 주입해야 할지" 모호함.
해결 방법:
@Qualifier: 특정 Bean을 이름으로 지정 (예: @Qualifier("naverPayGateway")).
@Primary: 기본으로 사용할 Bean 지정.
단일 구현체 사용: 불필요한 구현체의 @Component 제거.
결론: 의존성 주입은 Spring의 핵심 기능으로, 컴포넌트 스캔과 함께 사용 시 더욱 강력해짐.
*/
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("의존성 주입 테스트");
        PaymentService paymentService = context.getBean("naverPayService", PaymentService.class);
        paymentService.processPayment("naver-100", 200.0);
        System.out.println();
        PaymentService kakaoPayService = context.getBean("kakaoPayService", PaymentService.class);
        kakaoPayService.processPayment("kakao-100", 200.0);

        ((AnnotationConfigApplicationContext)context).close();
    }
}
