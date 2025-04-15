package com.metaverse.aop.section02.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/*
* AOP를 활성화한다.
*
* @EnableAspectAutoProxy
* AOP를 스프링에서 활성화할 때 사용하는 어노테이션이다.
* - 스프링 aop는 기본적으로 프록시 기반으로 동작하며, 이 어노테이션이 있어야 @Aspect(관심사)가 적용된다.
* - 프록시 생성을 통해 Pointcut과 advice 빈에 적용
* - 기본적으로 런타임에 프록시를 생성하며, AspectJ의 컴파일 타임 위임과는 다르다.
*
* - AspectJ란 : AspectJ는 java용 aop 프레임워크로 강력한 PointCut 표현식과 다양한 위빙 방식을 제공한다.
*               스프링 aop는 AspectJ의 문법을 차용하지만, 전체 기능을 사용하지는 않는다.
*               AspectJ는 프록시 없이도 직접 바이트코드를 수정할 수 있어 더 유연하지만 설정이 복잡하다.
* - 위빙이란 : aop에서 Aspect(부가 기능)을 핵심 로직에 삽입하는 과정이다
*   - 컴파일 타임 위빙 : AspectJ에서 소스 코드를 컴파일할 때 바이트코드에 Aspect를 삽입
*   - 로드 타임 위빙 : 클래스 로더가 클래스를 로드할 때 Aspect를 삽입
*   - 런타임 위빙 : 스프링 aop가 사용하는 방식으로 런타임에 프록시 객체를 생성해 Aspect를 적용
*   - 스프링 aop는 런타임 위빙만 지원하며, 프록시를 통해 동작하므로 final 클래스나 private 메서드는 적용 불가능하다.
* */
@Configuration
@ComponentScan(basePackages = "com.metaverse.aop.section02")
@EnableAspectJAutoProxy
public class AppConfig {
}
