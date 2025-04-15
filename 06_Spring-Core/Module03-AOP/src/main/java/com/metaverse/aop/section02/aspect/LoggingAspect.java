package com.metaverse.aop.section02.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/*
 * 로깅과 관련된 횡단 관심사를 모듈화한 Aspect 클래스
 *
 * @Aspect : 이 클래스가 AOP의 관점임을 선언한다.
 *   - pointCut(어디에 적용할지)와 Advice(무엇을 할지)를 하나의 단위로 묶는다.
 *   - AspectJ 프레임워크에서 제공하며, 스프링 aop와 통합되어 사용된다.
 * @Component: 스프링 컨테이너가 이 클래스를 bean으로 등록하도록 지시한다.
 *   - AOP가 동작하기 위해서는 스프링이 관리하는 빈이어야 프록시가 생성되어 적용할 수 있다.
 * */
@Aspect
@Component
public class LoggingAspect {

    /*
     * @Before : 대상 메서드가 실행되기 전에 실행되는 Advice(부가기능)을 정의
     *   로깅, 인증, 초기화 작업에 주로 사용된다.
     *
     * pointcut 표현식 : execution(* com.metaverse.aop.section02.MemberService.*(..))
     * - execution : 메서드 실행 시점을 타겟팅 하는 pointcut 디자인 패턴
     * - * 모든 반환 타입
     *
     * - com.metaverse.aop.section02.MemberService : 패키지 명부터 클래스까지 지정
     * - .* : memberService 클래스에 있는 모든 메서드를 의미
     * - (..) : 매개 변수가 0개 이상인 모든 경우를 의미
     * */
    @Before("execution(* com.metaverse.aop.section02.MemberService.*(..))")
    // MemberService 클래스에 있는 모든 메서드 실행 전엔 아래 코드를 실행하겠다.
    public void logBefore(JoinPoint joinPoint) {
        /*
         * joinPoint
         * Advice가 적용되는 지점에 대한 정보를 제공하는 객체
         * 메서드 이름, 매개변수, 타겟 객체 등의 정보르 얻을 수 있다.
         * */
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        StringBuilder argStr = new StringBuilder();
        for (Object arg : args) {
            argStr.append(arg).append(", ");
        }

        System.out.println("[로그] " + methodName + "메소드 실행 시작, 매개변수 " + argStr);
    }

    /*
     * @AfterThrowing : 대상 메서드 실행 중 예외가 발생했을 때 실행되는 advice이다.
     *                   예외 로깅, 예외복구 로직 등에서 사용된다.
     * */
    @AfterThrowing(
            pointcut = "execution(* com.metaverse.aop.section02.MemberService.*(..))",
            throwing = "exception"
    )
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[로그] " + methodName + "메서드 실행 중 예외 발생함" + exception.getMessage());
    }

    /*
     * @AfterReturning : 대상 메서드가 정상적으로 실행을 완료하고 값을 반환한 후 실행되는 advice이다.
     *                   반환값을 확인하거나 후처리 작업에 유용하다.
     * */
    @AfterReturning(
            pointcut = "execution(* com.metaverse.aop.section02.MemberService.*(..))",
            returning = "returningResult"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object returningResult) {

        String methodName = joinPoint.getSignature().getName();

        System.out.println("[로그] " + methodName + "메소드 정상 종료, 반환값 : " + returningResult);
    }

    /*
     * After : 대상 메서드 실행이 완료된 후 실행되는 Advice
     *       정상 종료든 예외가 발생하든 상관없이 항상 실행된다.
     *       자원을 정리하거나 로깅 등에 유용하게 사용된다.
     * */
    @After("execution(* com.metaverse.aop.section02.MemberService.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();

        System.out.println("[로그] " + methodName + "호출 종료");
    }

}
