package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

        /*appconfig 이전의, 구현체를 직접 의존하는 코드
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();*/

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        //우선 db(현재는 메모리)에 넣음
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);

        //주문 정보 출력(toString)
        System.out.println("order = " + order);
        //계산된 금액 출력
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
