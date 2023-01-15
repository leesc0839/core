package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
/*@RequiredArgsConstructor*/
public class OrderServiceImpl implements OrderService {

/*    // orderService 의 구현체는 MemberRepository 와 discountPolicy 를 의존한다. -> dip 위반
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();*/

    private final MemberRepository memberRepository; // dip 지키고 있음.
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) { // di
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 회원 정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인을 넘기는 것
        // 단일 책임의 원칙이 잘 지켜진 예시 -> 할인에 관한 작업은 결국 discountPolicy 에서만 쓰인다.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository instance() {
        return memberRepository;
    }
}
