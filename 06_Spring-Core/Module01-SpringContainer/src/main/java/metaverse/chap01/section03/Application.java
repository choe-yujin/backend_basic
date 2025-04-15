package metaverse.chap01.section03;

import metaverse.chap01.section03.config.AppConfig;
import metaverse.chap01.section03.service.PaymentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        String orderId = "order-123";
        double amount = 100.00;

        // 싱글톤 스코프 테스트
        System.out.println("=========싱글톤 스코프 테스트 =========");
        PaymentService singlePay = context.getBean("singlePay", PaymentService.class);
        boolean result = singlePay.processPayment(orderId, amount);
        System.out.println("싱글톤페이1의 마지막 주문 : " + singlePay.getLastOrderId());

        PaymentService singlePay2 = context.getBean("singlePay", PaymentService.class);
        System.out.println("싱글톤페이2의 마지막 주문 : " + singlePay2.getLastOrderId());

        System.out.println(singlePay2 == singlePay); // true
        System.out.println();

        // 프로토타입 스코프 테스트
        System.out.println("=========프로토타입 스코프 테스트 =========");
        PaymentService protoPay = context.getBean("protoPay", PaymentService.class);
        protoPay.processPayment(orderId, amount);
        System.out.println("프로토페이1의 마지막 주문 : " + protoPay.getLastOrderId());

        PaymentService protoPay2 = context.getBean("protoPay", PaymentService.class);
        System.out.println("프로토페이2의 마지막 주문 : " + protoPay2.getLastOrderId());

        System.out.println(protoPay2 == protoPay); // false
    }
    /*
    * =========싱글톤 스코프 테스트 =========
    결제 처리를 시작합니다. 주문 id:order-123,금액 : 100.0
    네이버 페이로 결제 진행합니다. 주문번호 : order-123금액 : 100.0
    결제 성공 처리됨
    싱글톤페이1의 마지막 주문 : order-123
    싱글톤페이2의 마지막 주문 : order-123
    true

    =========프로토타입 스코프 테스트 =========
    결제 처리를 시작합니다. 주문 id:order-123,금액 : 100.0
    네이버 페이로 결제 진행합니다. 주문번호 : order-123금액 : 100.0
    결제 성공 처리됨
    프로토페이1의 마지막 주문 : order-123
    프로토페이2의 마지막 주문 : null
    false
    * */
}
