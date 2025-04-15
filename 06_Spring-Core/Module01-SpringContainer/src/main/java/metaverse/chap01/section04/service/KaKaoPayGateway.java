package metaverse.chap01.section04.service;

/*
* 카카오페이 결제 게이트웨어 구현체
* 실제 결제 처리를 담당하는 클래스
* */
public class KaKaoPayGateway implements PaymentInterface {

    @Override
    public boolean processPayment(String orderId, double amount) {
        System.out.println("카카오 페이로 결제 진행합니다. 주문번호 : " + orderId + "금액 : " + amount);
        return true;
    }
}
