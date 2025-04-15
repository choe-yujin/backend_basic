package com.metaverse.aop.section01;

public class Application {
    public static void main(String[] args) {
        System.out.println("AOP를 적용하지 않았을 때 문제 확인");

        MemberService memberService = new MemberService();

        try {
            System.out.println("회원가입 테스트 하기");
            Member member = new Member("Bob", "1234", "user@gmail.com", "010- 1111 - 2222", "user");
            memberService.registerMember(member);

            System.out.println("회원조회 테스트");
            Member findMember = memberService.getMember("user@gmail.com");
            System.out.println(findMember);
        } catch (Exception e) {
            System.out.println("오류 발생됨");
        }
    }
}
