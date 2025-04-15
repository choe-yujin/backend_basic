package com.metaverse.aop.section02;

import com.metaverse.aop.section02.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        System.out.println("===========aop 적용 후 - 개선된 구조 ===========");

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = (MemberService) context.getBean("memberService");

        try {
            System.out.println("회원등록 테스트");
            Member member = new Member("Bob", "1234", "user@gmail.com", "010- 1111 - 2222", "user");
            memberService.registerMember(member);

//            System.out.println("회원조회 테스트");
//            Member findMember = memberService.getMember("user@gmail.com");
//            System.out.println(findMember);
        } catch (Exception e) {
            System.out.println("오류 발생됨");
            e.printStackTrace();
        }
    }
}
