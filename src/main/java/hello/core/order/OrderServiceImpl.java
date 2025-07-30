package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    //여기서는 할인에 대해서는 고려하지 않게끔 설계했음.(단일책임원칙은 지킨 것)
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //회원 정보 조회
        Member member = memberRepository.findById(memberId);
        //할인 가격 계산
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //최종 생성된 주문 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
