package com.metaverse.injection.chap01.section03.strategy.setter;

import com.metaverse.injection.chap01.section03.service.Paymentinterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*
세터 주입 (Setter Injection)
@Autowired를 Setter 메서드에 적용하여 의존성 주입.
장점:
선택적 의존성: 주입이 필수가 아님, 기본값 설정 가능.
런타임 변경 가능: 의존성을 동적으로 교체.
단점:
불변성 보장 불가: Setter로 언제든 변경 가능.
주입 누락 위험: Setter 호출 안 되면 null 상태로 사용 가능.
의존성 명확성 부족: 필수 의존성이 코드상으로 드러나지 않음.
예: 결제 게이트웨이를 실행 중에 변경하고 싶을 때 사용.
*/
@Service
public class PaymentServiceSetter {
    private Paymentinterface paymentGateway; // final 키워드가 붙어 있지 않다.
    // bean 생성 이후에도 바뀔 수 있다.
    // 바뀜에 따라 내가 의도하지 않은 bean이 주입되게 할 수도 있음
    // 구현체가 없는 상태로 bean이 생성될 수도 있다. new PaymentServiceSetter();
    // paymentServiceSetter.processPayment("order123", 200.0); 런타임에서야 구현체가 없어서 오류 남
    // context.getBean(PaymentServiceSetter.class); 라고 생성하면 문제는 없음. 하지만 setter방식은 잘 안 씀

    @Autowired
    public PaymentServiceSetter(Paymentinterface paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public boolean processPayment(String orderId, double amount) {
        System.out.println("결제 처리를 시작합니다. 주문 ID: " + orderId + ", 금액 : " + amount);
        boolean result = paymentGateway.processPayment(orderId, amount);

        if (result) {
            System.out.println("결제 성공");
        } else {
            System.out.println("결제 실패");
        }
        return result;
    }
}
