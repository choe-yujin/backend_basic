package com.metaverse.injection.chap01.section03.service;

import org.springframework.stereotype.Component;

@Component
public class NaverPayGateway implements Paymentinterface {
    @Override
    public boolean processPayment(String orderId, double amount) {
        System.out.println("Naver페이로 결제 진행합니다. 주문id : " + orderId + ", 결제금액 : " + amount);
        return true;
    }
}
