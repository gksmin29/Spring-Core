package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//final이 붙은 필드를 파라미터로 받는 생성자를 만들어 준다.
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    //여기서는 할인에 대해서는 고려하지 않게끔 설계했음.(단일책임원칙은 지킨 것)
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; //인터페이스에만 의존하도록 변경

    /*구현체를 참조하는 것을 막기 위한 주석 처리
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();*/


    //@RequiredArgsConstructor로 생성자를 만들었으므로 주석 처리
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //회원 정보 조회
        Member member = memberRepository.findById(memberId);
        //할인 가격 계산
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //최종 생성된 주문 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
