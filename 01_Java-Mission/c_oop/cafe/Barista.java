package mission.c_oop.cafe;

import mission.c_oop.beverage.Menu;
import mission.c_oop.beverage.coffee.Americano;
import mission.c_oop.beverage.coffee.Cafelatte;
import mission.c_oop.beverage.coffee.Cappuccino;
import mission.c_oop.beverage.coffee.Espresso;

public class Barista {
    // 바리스타는 최근 주문받은 메뉴의 가격을 기억한다.
    private int menuPriceFromCustomer; // 기억해야하는 메뉴 가격

    // 사용자에게 메뉴판을 보여준다.
    public void showMenu() {
        Menu menu = new Menu();
        menu.printMenu();
    }

    // 사용자가 선택한 메뉴의 가격을 알려준다.
    public void tellPrice(int menuNum) {
        String menuName = "";

        Americano americano = new Americano();
        Cafelatte cafelatte = new Cafelatte();
        Cappuccino cappuccino = new Cappuccino();
        Espresso espresso = new Espresso();

        switch (menuNum) {
            case 1 -> {
                menuPriceFromCustomer = americano.getPrice();
                menuName = americano.getName();
            }
            case 2 -> {
                menuPriceFromCustomer = cafelatte.getPrice();
                menuName = cafelatte.getName();
            }
            case 3 -> {
                menuPriceFromCustomer = espresso.getPrice();
                menuName = espresso.getName();
            }
            case 4 -> {
                menuPriceFromCustomer = cappuccino.getPrice();
                menuName = cappuccino.getName();
            }
            //예외처리 생략
        }

        System.out.println("바리스타; " + "주문하신 " + menuName + "는 " + menuPriceFromCustomer + "원입니다. ");
        System.out.print("금액을 주세요 -> ");
    }

    // 사용자의 결제 금액을 확인한 후 응답한다. : 결제, 응답, 제조
    // 1. 지불한 금액 > 메뉴 가격 -> 거스름돈 & 주문한 메뉴가 나왔습니다.
    // 2. 지불한 금액 == 메뉴 가격 -> 주문한 메뉴가 나왔습니다.
    // 3. 지불한 금액 < 메뉴 가격 -> 부족한 금액 안내, 추가 지불 요청
    public int takeOrder(int money) {
        int change = money - menuPriceFromCustomer;

        if (change > 0) {
            System.out.print("바리스타; 거스름돈 " + change + "원 입니다. ");
            System.out.println("주문한 메뉴가 나왔습니다.");
        } else if (change == 0) {
            System.out.println("바리스타; 주문한 메뉴가 나왔습니다.");
        } else {
            System.out.print("바리스타; 돈이 " + (-1 * change) + "원 부족합니다. ");
        }

        return change;
    }

}
