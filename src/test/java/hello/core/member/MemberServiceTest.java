package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {


    MemberService memberService;

    //각 테스트 전에 무조건 실행됨
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    //가입한 회원과 조회한 회원이 같은 지 테스트
    @Test
    void join() {
        //given(이런 환경이 주어졌을 때)
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when(이렇게 했을 때)
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then(이렇게 된다)
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
