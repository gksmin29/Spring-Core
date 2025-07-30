package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;

public class OrderApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

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
