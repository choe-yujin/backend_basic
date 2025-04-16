package metaverse.valueobjct.chap01.section01;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        Reservation reservation = new Reservation(
                "김철수",
                "101호",
                2,
                LocalDate.of(2025, 8, 1),
                LocalDate.of(2025, 8, 3),
                10000
        );

        System.out.println("예약 숙박일수 : " + reservation.calculateNights());

        reservation.setTotalPrice(-5000);
        System.out.println("변경된 총 가격 : " + reservation.getTotalPrice());

        /*
    📌 Value Object(VO)의 필요성
    VO를 사용하지 않고 개별 필드를 직접 다루는 방식은 다음과 같은 문제점을 초래할 수 있다:*
    가독성 저하:
    생성자에 많은 파라미터가 전달되면 코드가 복잡해지고, 각 파라미터가 무엇을 의미하는지 직관적으로 이해하기 어려움.
    예: Order 생성자에 9개의 파라미터가 전달되며, 파라미터의 순서와 의미를 기억해야 함.
    *
    유지보수성 저하:
    필드 추가/삭제 시 생성자와 호출 코드 모두 수정해야 함.
    예: Order에 새로운 필드(예: 배송비)가 추가되면 생성자와 모든 호출 코드를 수정해야 함.
    *
    타입 안정성 부족 :
    개별 필드를 문자열이나 기본 타입으로 전달하면 타입 안정성이 떨어지고, 잘못된 데이터가 전달될 가능성이 높음.
    예: 주소 필드("서울시 강남구", "테헤란로 123", "12345")를 각각 문자열로 전달하면, 주소 형식이 올바른지 검증하기 어려움.
    *
    코드 중복:
    동일한 데이터 그룹(예: 주소, 결제 정보)을 여러 객체에서 반복적으로 정의해야 함.
    예: Order와 Delivery가 동일한 주소 정보를 필요로 할 경우, 중복된 필드 정의가 필요.
    *
    불변성 보장 어려움:
    개별 필드를 직접 다루면 불변 객체로 만들기 어려움.
    예: Order 객체가 생성된 후 필드가 변경될 가능성이 있음.
    *
    비즈니스 로직 분산:
    VO를 사용하지 않으면 데이터 검증 로직이 여러 곳에 분산되어 일관성 유지가 어려움.
    예: 주소 형식이 올바른지 검증하는 로직을 Order와 Delivery에서 각각 구현해야 함.
    *
    VO를 사용하면 이러한 문제점을 해결할 수 있습니다:
    관련 데이터를 하나의 객체로 묶어 가독성과 유지보수성을 높임.
    타입 안정성과 불변성을 보장.
    데이터 검증 로직을 VO에 캡슐화하여 비즈니스 로직을 일관되게 관리.
    */
        Reservation reservation2 = new Reservation(
                "김영희",
                "202호",
                1,
                LocalDate.of(2025, 9, 5),
                LocalDate.of(2025, 9, 3),
                7000
        );

        System.out.println("예약 숙박일 수" + reservation2.calculateNights());
    }
}

