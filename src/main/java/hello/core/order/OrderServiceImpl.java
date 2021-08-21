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
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

   // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
   //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
   private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


   // @Autowired  생성자 주입이 하나이면 Autowired 생략 가능
    // @RequiredArgsConstructor를 사용하면 final 붙은 생성자 생성

    //Qualifier은 생성자주입, 수정자주입, 필드 주입에도 넣을 수 있다.

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,/* @MainDiscountPolicy*//*@Qualifier("mainDiscountPolicy")*/ DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /*일반메스드 주입일경우 final 삭제

     */
//    private MemberRepository memberRepository2;
//    private  DiscountPolicy discountPolicy2;
//    @Autowired //일반메소드 주입 => 생성자 주입, 수정자(setter) 주입에서 수정 가능하기때문에 잘 사용하지않는다
//
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository2 = memberRepository;
//        this.discountPolicy2 = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId, itemName, itemPrice,discountPrice);
    }
    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
