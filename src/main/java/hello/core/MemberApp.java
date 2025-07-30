package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        /*스프링을 사용 하지 않을 때의 의존성 주입
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();*/

        /*appconfig 이전의, 구현체를 직접 의존하는 코드
        MemberService memberService = new MemberServiceImpl();*/

        //스프링을 이용해서 컨테이너에서 빈 꺼내기
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(AppConfig.class);
        //파라미터 = 이름과 타입
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        //회원 가입
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        //회원 조회
        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());

    }
}
