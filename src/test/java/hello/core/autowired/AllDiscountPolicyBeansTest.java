package hello.core.autowired;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Map;

public class AllDiscountPolicyBeansTest {

    @Test
    @DisplayName("할인 정책 관련 객체를 담고 있는 서비스 개발.")
    void test() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DiscountPolicyAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);

        Member testMember = new Member(1L, "test", Grade.VIP);

        int discountPrice = discountService.getDiscountPrice(testMember, 20000, "rateDiscountPolicy");
        Assertions.assertThat(discountPrice).isEqualTo(2000);
    }


    @Service
    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap; // 여기에 주입 받겠지

        @Autowired // 지워보자
        public DiscountService(Map<String, DiscountPolicy> policyMap) {
            this.policyMap = policyMap;
        }

        public int getDiscountPrice(Member testMember, int price, String keyOfPolicy) {
            DiscountPolicy discountPolicy = policyMap.get(keyOfPolicy);
            return discountPolicy.discount(testMember, price);
        }
    }


    @Configuration
    static class DiscountPolicyAppConfig {

        @Bean
        DiscountPolicy discountPolicy() {
            return new FixDiscountPolicy();
        }

        @Bean
        DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

    }
}
