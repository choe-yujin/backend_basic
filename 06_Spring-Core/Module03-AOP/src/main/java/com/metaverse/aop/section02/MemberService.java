package com.metaverse.aop.section02;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MemberService {
    private Map<String, Member> memberRepository = new HashMap<String, Member>();

    public MemberService() {
    }

    public void registerMember(Member member) {

        if (member.getEmail() == null || member.getEmail().isEmpty()) {
            throw new IllegalArgumentException("이메일은 필수입니다.");
        }

        if (member.getPassword() == null || member.getPassword().isEmpty()) {
            throw new IllegalArgumentException("비밀번호는 8자 이상이어야 합니다.");
        }

        if (memberRepository.containsKey(member.getEmail())) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다. : " + member.getEmail());
        }

        memberRepository.put(member.getEmail(), member);
        // 비즈니스 처리 로직 종료

    }

    public Member getMember(String email) {

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("이메일을 입력해주세요");
        }

        Member member = memberRepository.get(email);
        if (member == null) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다. : " + email);
        }

        // 핵심 로직은 종료

        return member;
    }

    public void updatePassword(String email, String currentPassword, String newPassword) {

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("이메일은 필수입니다.");
        }

        if (currentPassword == null || currentPassword.isEmpty()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }

        if (newPassword == null || newPassword.isEmpty()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }

        if (newPassword.length() < 8) {
            throw new IllegalArgumentException("비밀번호는 8글자 이상이어야 합니다.");
        }

        Member member = memberRepository.get(email);

        if (member == null) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다. : " + email);
        }

        if (!member.getPassword().equals(currentPassword)) {
            throw new IllegalArgumentException("현재 비밀번호와 일치하지 않습니다.");
        }

        member.setPassword(newPassword);
        memberRepository.put(email, member);
        // 비즈니스  로직 종료

    }


    public void deleteMember(String email) {

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않은 회원입니다.");
        }
        if (!memberRepository.containsKey(email)) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        // 회원 삭제
        memberRepository.remove(email);

    }

}
