package com.metaverse.injection.chap01.section03;

import com.metaverse.injection.chap01.section03.config.AppConfig;
import com.metaverse.injection.chap01.section03.strategy.constructor.PaymentServiceConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("의존성 주입 테스트");
        PaymentServiceConstructor paymentService = context.getBean("paymentServiceConstructor", PaymentServiceConstructor.class);
        paymentService.processPayment("naver-100", 200.0);

        ((AnnotationConfigApplicationContext)context).close();
    }
}
