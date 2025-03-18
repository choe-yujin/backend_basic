package oop.mission.cafe;

public class Cafe {
    // 바리스타가 있다
    // 손님이 있다
    // 메뉴판이 있다
    // 커피를 판매한다
    public static void main(String[] args) {
        // 1. 사용자가 커피숍에 입장
        Customer customer = new Customer();

        // 2. 바리스타가 사용자에게 미리 준비된 메뉴판을 보여준다.
        Barista barista = new Barista();
        barista.showMenu();

        // 3. 사용자는 메뉴판에서 원하는 메뉴를 선택한다.
        int menuNum = customer.selectMenu();

        // 4. 바리스타는 사용자가 선택한 메뉴의 가격을 알려준다.
        barista.tellPrice(menuNum);

        // 5. 사용자는 결제할 금액을 입력한다.
        int money = customer.purchaseMenu();

        // 6. 바리스타는 결제 금액을 확인한 후 주문받고 거스름돈
        barista.takeOrder(money);
    }
}
