package com.metaverse.injection.chap01.section02.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/*
@Primary:
동일 타입의 Bean이 여러 개일 때, 기본으로 주입될 Bean을 지정.
역할: 의존성 주입 시 충돌(NoUniqueBeanDefinitionException)을 해결하며, 명시적으로 우선순위를 부여.
예시: PaymentInterface를 구현한 KakaoPayGateway와 NaverPayGateway가 모두 @Component로 등록된 경우,
@Primary가 붙은 KakaoPayGateway가 PaymentService에 기본 주입됨.
사용 시나리오:
기본 구현체 지정: 여러 결제 게이트웨이 중 하나를 기본으로 사용하고 싶을 때.
테스트 환경: Mock 객체를 기본으로 설정할 때.
주의:
@Primary는 한 타입에 대해 하나의 Bean에만 적용해야 함(중복 시 오류).
@Qualifier가 @Primary보다 우선순위가 높음(명시적 지정이 더 강력).
실무 팁: @Primary 대신 @Qualifier를 사용하면 더 명확한 의도를 전달 가능.
*/

@Component
@Primary
public class KaKaoPayGateway implements Paymentinterface {

    @Override
    public boolean processPayment(String orderId, double amount) {
        System.out.println("카카오페이로 결제 합니다.: " + orderId + "가격 " + amount);
        return true;
    }
}
