package com.metaverse.aop.section02.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/*
* @Around : 메서드 실행 전후에 실행되는 Advice를 정의하는 어노테이션
* - 가장 강력한 Advice 타입으로, 대상 메서드의 실행 자체를 제어할 수 있음
* - 메서드 호출 전후에 추가 로직을 삽입하거나, 아예 메서드 실행을 막을 수도 있음
* - Pointcut 표현식과 함께 사용되어 적용 대상을 지정함
* */
@Aspect
@Component
public class PerformanceAspect {

    @Around("execution(* com.metaverse.aop.section02.MemberService.*(..))")
    public Object measurePerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        /*
        * ProceddingjoinPoint는 스프링 aop에서 @Around advice에서 사용되는 특별한 인터페이스다.
        * 일반적인 JoinPoint를 확장한 것으로, 대상 메서드의 실행을 직접 제어할 수 있는 기능을 제공한다.
        *
        * - proceedJoinPoint.getSignature() : 호출된 메서드의 시그니처(이름, 반환 타입 등)을 반환한다.
        * - getName() : 메서드의 이름을 추측
        * */
        String methodName = joinPoint.getSignature().getName();
        long startTime = System.currentTimeMillis();

        try {
            /*
            * ProceedingjoinPoint.proceed() : 원본 메서드를 실행하는 메서드
            * - @Around에서 반드시 호출해야 원본 메서드가 실행된다.
            * - 호출하지 않으면 원본 메서드가 실행되지 않는다.
            * - 반환값은 원본 메서드의 결과이며 Object 타입으로 모든 타입을 수용하게 된다.
            * */
            Object result = joinPoint.proceed();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            long endTime = System.currentTimeMillis();
            System.out.println("[성능] " + methodName + ", 실행 시간 : " + (endTime - startTime) + "ms");
        }
    }
}
