package metaverse.chap01.section04.service;

public interface PaymentInterface {
    /**
     * 결제를 처리하는 메소드
     * @param orderId 주문 ID
     * @param amount 결제 금액
     * @return 결제 성공 여부
     * */
    boolean processPayment(String orderId, double amount);
}