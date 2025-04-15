package com.metaverse.injection.chap01.section01;

import com.metaverse.injection.chap01.section01.config.AppConfig;
import com.metaverse.injection.chap01.section01.service.PaymentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//        String[] beanNames = context.getBeanDefinitionNames();
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }

        System.out.println("컴포넌트 스캔 테스트");
        // 꺼내는 방식 2가지
        PaymentService paymentService = context.getBean("paymentService", PaymentService.class);
        //PaymentService paymentService2 = (PaymentService)context.getBean("PaymentService");

        paymentService.processPayment("scan-100", 200.0);

        ((AnnotationConfigApplicationContext)context).close();
    }
}
