package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    Long memberId = 1L;
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("vip 일 때 할인 가격")
    void vip_o(){
        //given
        Member member = new Member(memberId, "vip", Grade.VIP);
        //when
        int discountPrice = discountPolicy.discount(member, 20000);

        //then
        assertThat(discountPrice).isEqualTo(2000);
    }

    @Test
    @DisplayName("vip 아닐 때 할인 가격")
    void vip_x(){
        //given
        Member member = new Member(memberId, "basic", Grade.BASIC);

        //when
        int discountPrice = discountPolicy.discount(member, 20000);

        //then
        assertThat(discountPrice).isEqualTo(0);
    }

}