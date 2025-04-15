package metaverse.chap01.section03.service;

public class PaymentService {

    private PaymentInterface paymentGateway;

    private String lastOrderId;

    public PaymentService(PaymentInterface paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String getLastOrderId() {
        return lastOrderId;
    }

    public boolean processPayment(String orderId, double amount) {
        System.out.println("결제 처리를 시작합니다. 주문 id:" + orderId + ",금액 : " + amount);

        this.lastOrderId = orderId;

        boolean result = paymentGateway.processPayment(orderId, amount);

        if (result) {
            System.out.println("결제 성공 처리됨");
        } else {
            System.out.println("결제 실패 처리됨");
        }
        return result;
    }
}

