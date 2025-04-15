package metaverse.chap01.section01;

import metaverse.chap01.section01.service.PaymentService;

public class Application {
    public static void main(String[] args) {
        System.out.println("스프링이 없이 객체 생성 및 사용");
        PaymentService paymentService = new PaymentService();

        String orderId = "order-1234";
        double amount = 100.0;

        boolean isTrue = paymentService.processPayment(orderId, amount);
        System.out.println("결제 결과 : " + (isTrue ? "성공" : "실패"));
        /*
         * 문제점 : 결제 게이트웨이를 카카오에서 네이버로 변경하려면
         * paymentService 클래스를 직접 수정해야 한다.
         * 1. kakaoPayGateway -> NaverPaygateWay 변경
         * 2. pay"() -> processPayment() 메소드 변경
         * 3. 인자의 순서 확인
         *
         * 이는 개방 -폐쇄의 원칙(OCP)를 위반한다.
         * */
    }
}
