package com.metaverse.injection.chap01.section02.config;

import com.metaverse.injection.chap01.section02.service.PaymentService;
import com.metaverse.injection.chap01.section02.service.Paymentinterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.metaverse.injection.chap01.section02")
public class AppConfig {
    @Bean
    public PaymentService kakaoPayService(Paymentinterface paymentinterface) {
        // kakaoPayGateway가 주입됨
        return new PaymentService(paymentinterface);
    }

    /*
    naverPayService Bean 정의
    @Qualifier("naverPayGateway"):
    역할: 동일 타입(PaymentInterface)의 Bean 중 특정 Bean을 이름으로 명시적으로 지정.
    사용 이유: KakaoPayGateway가 @Primary로 기본 설정되어 있어, NaverPayGateway를 주입하려면 명시적 지정 필요.
    동작: "naverPayGateway"라는 Bean 이름의 NaverPayGateway를 PaymentService에 주입.
    예: PaymentInterface 구현체가 여러 개일 때, 원하는 결제 게이트웨이를 정확히 선택.
    이점:
    명확성: 의존성 주입 의도를 코드로 명확히 표현.
    유연성: @Primary와 달리 특정 상황에서 다른 Bean 선택 가능.
    주의:
    @Qualifier는 @Primary보다 우선순위가 높음.
    잘못된 Bean 이름 지정 시 NoSuchBeanDefinitionException 발생.
    실무 팁: @Qualifier는 디버깅 시 주입된 Bean을 추적하기 쉬워 유지보수에 유리.
    */
    @Bean
    public PaymentService naverPayService(
            @Qualifier("naverPayGateway") Paymentinterface paymentinterface) { // Qualifier = bean타입 명시적으로 지정할 때 사용
        return new PaymentService(paymentinterface);
    }

}
