package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// 순수 자바코드로 orderServiceImpl을 테스트 하고 싶다 !

class OrderServiceImplTest {
    @Test
    void orderServiceImplTest() {
        //  OrderServiceImpl orderService = new OrderServiceImpl(); // 생성자 주입을 사용하면 바로 빨간줄 발생해서 컴파일 오류 발생시킴
        MemberRepository memberRepository = new MemoryMemberRepository();
        Member newMember = new Member(1L, "testMember", Grade.VIP);
        memberRepository.save(newMember);

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());

        Order itemA = orderService.createOrder(1L, "itemA", 10000);

        Assertions.assertThat(itemA.getDiscountPrice()).isEqualTo(1000);
    }
}