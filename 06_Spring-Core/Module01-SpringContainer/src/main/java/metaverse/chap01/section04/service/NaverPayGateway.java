package metaverse.chap01.section04.service;

public class NaverPayGateway implements PaymentInterface {
    @Override
    public boolean processPayment(String orderId, double amount) {
        System.out.println("네이버 페이로 결제 진행합니다. 주문번호 : " + orderId + "금액 : " + amount);
        return true;
    }
}
