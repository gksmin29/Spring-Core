package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        //컨테이너 생성
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,
                DiscountService.class);

        //DiscountService 빈 가져오기
        DiscountService discountService = ac.getBean(DiscountService.class);
        //새 멤버 생성
        Member member = new Member(1L, "userA", Grade.VIP);
        //
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPolicy = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPolicy).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        //@Autowired
        //fixDiscountPolicy, rateDiscountPolicy 주입
        public DiscountService(Map<String, DiscountPolicy> policyMap,
                               List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }
        //discountCode =
        public int discount(Member member, int price, String discountCode) {
            //Map에서 멤버의 discountPolicy에 해당하는 Policy를  찾아서 꺼냄
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            //할인 후 할인액 반환
            return discountPolicy.discount(member, price);
        }
    }
}
