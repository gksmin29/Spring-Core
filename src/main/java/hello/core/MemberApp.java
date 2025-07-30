package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class MemberApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        /*appconfig 이전의 구현체를 직접 의존하는 코드
        MemberService memberService = new MemberServiceImpl();*/

        //순수 자바 코드를 사용한 회원 가입, 조회
        //회원 가입
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        //회원 조회
        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());

    }
}
