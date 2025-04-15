package metaverse.chap01.section01.service;

/*
 * 기존 java 개발 방식의 문제점
 * kakappay -> naverpay로 바꿔볼 때
 * 1. 강한 결합도
 * 2. 객체 생성의 책임 -> PaymentService가 -> kakaoPay에서 -> naverPay로 변경되어야 함.
 * */
public class PaymentService {
    private KaKaoPayGateway paymentGateway;
    private NaverPayGateway naverPayGateway;

    public PaymentService() {
        //this.paymentGateway = new KaKaoPayGateway();
        this.naverPayGateway = new NaverPayGateway();
    }

    public boolean processPayment(String orderId, double amount) {
        System.out.println("결제 처리를 시작합니다. 주문 id:" + orderId + ",금액 : " + amount);

        //boolean result = paymentGateway.pay(orderId, amount);
        boolean result = naverPayGateway.processPayment(orderId, amount);
        if (result) {
            System.out.println("결제 성공 처리됨");
        } else {
            System.out.println("결제 실패 처리됨");
        }
        return result;
    }
}

