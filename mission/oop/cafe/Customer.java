package oop.mission.cafe;

import java.util.Scanner;

public class Customer {
/*    // 주문할 돈을 가지고 있다. // 일단 생략
    private int money;

    public Customer(int money) {
        this.money = money;
    }*/

    // 커피숍에 입장한다.
    // 메뉴를 선택한다.
    public int selectMenu() {
        System.out.print("메뉴 번호를 입력해주세요 -> ");
        Scanner sc = new Scanner(System.in);
        int menuNum = sc.nextInt();
        int result = 0;
        switch (menuNum) {
            case 1 -> result = 1;
            case 2 -> result = 2;
            case 3 -> result = 3;
            case 4 -> result = 4;
        }
        return result;
    }

    // 결제한다. : 결제할 금액을 입력한다.
    public int purchaseMenu() {
        Scanner sc = new Scanner(System.in);
        int money = sc.nextInt();
        System.out.println("사용자; 여기 " + money + "원 받으세요.");
        return money;
    }
}
