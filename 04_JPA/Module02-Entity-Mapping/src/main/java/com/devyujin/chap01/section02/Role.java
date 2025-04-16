package com.devyujin.chap01.section02;

/**
 * Enum 타입 선언
 * > Enum은 자바의 타입 안전성(type-safety)를 제공하면서
 *   제한된 값만 사용할 수 있도록 강제한다.
 * > Enum은 여러 관련 상수를 그룹화하여 정의할 수 있는 특별한 데이터 타입니다.
 *   사용자가 정의한 상수들의 집합을 생성할 수 있다.
* */
public enum Role {
    // ENUM 상수 : 각 상수는 대문자로 표기하며,
    // 이들은 ROLE 타입의 인스턴스이다.
    STUDENT("학생"),
    INSTRUCTOR("강사"),
    ADMIN("관리자");

    /* 아래와 같이 추가적인 속성을 정의할 수 있다. */
    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
    * ENUM의 장점
     * 1. 타입 안전성 : ENUM 타입을 사용하면 잘못된 값의 할당을 방지한다.
     *    예) Role role = Role.STUDENT;
     *
     * 2. 가독성 : 코드의 의미를 명확하게 전달한다.
     *
     * 3. 네임스페이스 : Role.STUDENT와 같이 네임스페이스를 사용하여 이름 충돌을 피할 수 있다.
     *
     * 4. 반복문 사용 : ENUM의 모든 값을 반복할 수 있다.
     *
     * 5. switch에서 사용 가능 : ENUM 상수를 switch 문에서 사용할 수 있다.
    * */

}
