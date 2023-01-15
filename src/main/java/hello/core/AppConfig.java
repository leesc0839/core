package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    appConfig 의 존재 이유 : dip, ocp 를 지키기 위해서.
 */
/*
    di : 런타임에 구현 객체를 생성하고 클라이언트에 전달한다. 그리고 주입
    di 장점 : 의존 관계 주입을 사용하면, 클라이언트 코드를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경할 수 있다.
    즉, 의존 관계 주입을 사용하면, 정적인 클래스 의존 관계를 변경하지 않고, 동적 인스턴스 의존관계를 쉽게 변경 가능.
 */

/*
    AppConfig -> Ioc 컨테이너 , Di 컨테이너
    ioc 컨테이너, Di 컨테이너 -> ioc를 일으킴. -> 제어 흐름에 관한 권한을 모두 가지고 있음.
 */
@Configuration
public class AppConfig { // 관리자 : 구현 객체를 생성과 연결을 담당.

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() { // 생성자 주입
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() { // 생성자 주입
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}


