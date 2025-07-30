package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    //MemberService를 반환하는 객체(Repository를 담은 Service를 반환)
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    //OrderService를 반환하는 객체(Repository와 DiscountPolicy를 담은 Service를 반환)
    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
