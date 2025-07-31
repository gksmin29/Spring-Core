package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자가 10000원 주문
        statefulService1.order("userA", 10000);
        //int userAPrice = statefulService1.order("userA", 10000); -> 이렇게 바꾸면 해결

        //ThreadB: B사용자가 20000원 주문(A의 주문과 조회 사이에 B가 끼어든 것)
        statefulService1.order("userB", 20000);
        //int userBPrice = statefulService1.order("userB", 20000); -> 이렇게 바꾸면 해결

        //ThreadA: 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();
        //ThreadA: 사용자A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
        System.out.println("price = " + price);
        //System.out.println("price = " + userAPrice); -> 이렇게 바꾸면 해결

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}