package metaverse.chap01.section01.service;

public class NaverPayGateway {
    public boolean processPayment(String orderId, double amount) {
        System.out.println("네이버페이 결제 진행합니다. 주문번호 : " + orderId + ", 금액 : " + amount);
        return true;
    }
}
