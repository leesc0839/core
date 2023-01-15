package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("spring container에 들어간 repository(bean)은 싱글톤 ?")
    void isEqualInstance() {
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        MemberRepository memberServiceRepository = memberService.instance();
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository orderServiceRepository = orderService.instance();

        assertThat(memberRepository).isEqualTo(memberServiceRepository);
        assertThat(memberServiceRepository).isEqualTo(orderServiceRepository);


    }

    @Test
    void configuration(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class); // AppConfig도 bean으로 등록이 된다.
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean.getClass() = " + bean.getClass());
        // appconfig에 @Configuration을 하지 않으면 bean들이 등록은 되지만, 싱글톤이 보장되지 않는다.
        // 심지어 appconfig의 MemberServiceImpl(memberRepository()) -> 의 memberRepository는 bean으로 등록되지도 않는다.
        // @Configuration은 CGLIB으로 appConfig를 상속받아 코드를 변형하여 싱글톤을 보장한다.



    }
}
