package com.metaverse.injection.chap01.section01.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;

/*

Spring 컴포넌트 스캔 설정
- @ComponentScan:
지정된 패키지에서 @Component, @Service 등의 어노테이션이 붙은 클래스를 탐지하여 Bean으로 등록.
basePackages: "com.metaverse.container.chap01.section01"로 설정하여 현재 패키지와 하위 패키지 모두 스캔.

- 동작 과정:
컨테이너 시작 시 지정된 패키지를 탐색.
@Component, @Service 등이 붙은 클래스(KakaoPayGateway, NaverPayGateway, PaymentService)를 인식.
해당 클래스들을 Bean으로 등록하고 의존성 주입 수행.

- 이점:
코드 간소화: @Bean으로 일일이 정의할 필요 없음.
유연성: 새로운 컴포넌트 추가 시 설정 변경 없이 자동 인식.
예: 새로운 결제 게이트웨이 클래스를 추가하면 스캔으로 바로 사용 가능.
*/
@Configurable
@ComponentScan(basePackages = "com.metaverse.injection.chap01.section01") // bean 탐색하기 위한 경로 지정
public class AppConfig {
/*

컴포넌트 스캔(@ComponentScan)을 활용하면 발생하는 이점:

코드 간소화 및 생산성 향상:
@Bean으로 개별 클래스를 수동 정의할 필요 없이, @Component, @Service 등의 어노테이션으로 자동 등록.
예: PaymentService, KakaoPayGateway를 @Bean 메서드로 작성하지 않아도 스캔으로 Bean 생성 가능.
결과: 설정 코드가 줄어들어 개발 속도 증가 및 가독성 개선.

유연한 확장성:
새로운 컴포넌트를 추가할 때 AppConfig를 수정하지 않고, 해당 패키지에 클래스만 추가하면 자동 인식.
예: 새로운 결제 게이트웨이(예: TossPayGateway)를 추가하면 별도 설정 없이 사용 가능.
결과: 애플리케이션 확장 시 유지보수 비용 감소.

의존성 주입 자동화:
@Autowired와 함께 사용하면 컴포넌트 간 의존성을 Spring이 자동으로 연결.
예: PaymentService가 PaymentInterface 구현체를 주입받을 때 수동 wiring 없이 동작.
결과: 개발자가 의존성 관리에 신경 쓸 필요 감소.

일관된 Bean 관리:
스캔된 Bean은 Spring IoC 컨테이너에 의해 일관되게 관리(생성, 초기화, 소멸).
예: PaymentService의 생명주기(생성 → 사용 → 소멸)가 @Bean 정의와 동일하게 적용.
결과: 수동 설정과 동일한 수준의 컨테이너 관리 보장.

개발 표준화:
@Component, @Service 등으로 계층별 역할 명시 가능(서비스, 리포지토리 등).
예: @Service로 비즈니스 로직 클래스 구분, @Component로 범용 모듈 구분.
결과: 팀 내 코드 구조 통일 및 협업 효율성 증가.

주의사항:
스캔 범위(basePackages)가 너무 넓으면 불필요한 클래스까지 Bean으로 등록될 수 있음 → 성능 저하 가능.
동일 타입의 Bean이 여러 개일 경우 충돌 발생 → @Qualifier 또는 @Primary로 해결 필요.
결론: 컴포넌트 스캔은 설정 작업을 줄이고, 유연성과 표준화를 제공하며, Spring의 IoC를 최대한 활용하게 해줌.
*/
}
