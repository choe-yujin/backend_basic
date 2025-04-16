package com.metaverse.inheritance.chap01.section01;

import com.metaverse.inheritance.chap01.section01.model.ClothingProduct;
import com.metaverse.inheritance.chap01.section01.model.ElectronicProduct;
import com.metaverse.inheritance.chap01.section01.model.FoodProduct;

import java.time.LocalDate;

/*
* 잘못된 설계 : 상속 없이 별도의 클래스로 관리
* - ElectronicProduct, ClothingProduct, FoodProduct의 공통 속성(name, price 등)을 가지지만, 별도의 클래스로 관리
* - 공통 속성을 각각 정의해야 하므로 코드 중복 발생
* - 데이터베이스에서도 별도의 테이블로 관리되므로, 공통 속성을 조회하거나 관리하기 위한 추가 로직 필요
* */
public class Application {
    public static void main(String[] args) {
        ElectronicProduct product = new ElectronicProduct("laptop", 999.999, "techBrand", 50, 20);
        ClothingProduct clothing = new ClothingProduct("T-shirt", 19.99, "FashionBrand",100, "M", "cotton");
        FoodProduct food = new FoodProduct("milk", 2.99, "foodBrand", 200, LocalDate.now().plusDays(7), true);

        System.out.println(product);
        System.out.println(clothing);
        System.out.println(food);

        /*
        * 상속을 사용하지 않으면 공통 속성을 관리하기 위한 추가 로직이 필요
        * 예. 모든 상품의 가격을 조회하려면 클래스를 각각 처리해야 함
        *
        * 문제점
        * - 공통 속성이 중복 정의됨
        * - 데이터베이스에서도 테이블이 별도로 생성되어야함.
        * - 공통 속성을 조회하기 위해서 세 테이블을 각각 조회해야 함.
        * - 새로운 상품 유형을 추가하면 새로운 클래스를 만들어야 함.
        *
        * 제품 삭제를 해야한다.
        * 그럼 모든 제품은 식품이든, 의류든, 가전제품이든 재고가 존재하기 때문에 제품으로 판매할 수 있는데
        * 각각의 재고를 감소하는 것을 개별적 클래스 내에서 해야한다.
        * 공통된 제품 클래스에서 재고 감소를 하게되면
        * 상속받고 있는 쪽에서 전부 사용할 수 있게 된다.
        * 개별적으로 관심사를 분리하지 않아도 된다.
        *
        * 각각의 클래스가 엔티티로써 존재한다.
        * 엔티티 만들고 테이블 생성해줘야 하는데,
        * 각각 개별적 테이블이 되는데
        * unionall이라는게 있는데, sql 쿼리 2개 합치는...
        * 이렇게 했을때 공통적으로 쓰고있는 타입이 맞는지 확인하기 어려운 난감한....
        *
        * 가전제품, 옷, 음식 다 쿼리를 각각 만들어줘야하는 문제가 있다.
        * 개별적으로 조인한 후에 자바에서 리스트로 담아서 가야됨.
        * 상속사용하지 않으면 필드명이 바뀐다면 모든 제품의 필드명을 다 바꿔줘야하는 문제.
        *
        * 상속전략으로 문제 해결하자.
        * 1. 싱글 테이블 전략
        * 하나의 테이블에 서브타입 엔티티를 준다.
        * */
    }
}
