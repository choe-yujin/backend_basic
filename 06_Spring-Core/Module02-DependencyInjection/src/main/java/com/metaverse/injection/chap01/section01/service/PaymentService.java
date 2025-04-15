package com.metaverse.injection.chap01.section01.service;

import org.springframework.stereotype.Service;

/*
* PaymentService 클래스
* - @Service
* - @Component의 특수화된 형태로, 서비스 계층(Service Layer)에 속하는 bean을 나타낸다.
* - 역할 : 비즈니스 로직 처리하는 클래스에 사용하며, sprint이 이를 bean으로 등록한다.
* - bean : 이름 paymentService
* */
@Service
public class PaymentService {
    private final KaKaoPayGateway paymentGateway;

    public PaymentService(KaKaoPayGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public boolean processPayment(String orderId, double amount) {
        System.out.println("결제 처리르 시작합니다. 주문 ID: " + orderId + ", 금액 : " + amount);
        boolean result = paymentGateway.processPayment(orderId, amount);

        if (result) {
            System.out.println("결제 성공");
        } else {
            System.out.println("결제 실패");
        }
        return result;
    }
}
