package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {
    @Test
    @DisplayName("기존의 AppConfig를 이용하면 객체가 호출 될 때 마다 생성된다.")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("singleton 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getSingletonService();
        SingletonService singletonService2 = SingletonService.getSingletonService();

        assertThat(singletonService1).isSameAs(singletonService2);
        //isSameAs -> 인스턴스가 같은지
        // equal -> 진짜 equal

        //  SingletonService singletonService = new SingletonService(); // 컴파일 오류가 나게 설계하는게 best

    }



    @Test
    @DisplayName("applicationContext 로 가져왔을 때, singleton 으로 관리 되는 지 ?")
    void usingSpringContainer() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);
        assertThat(memberService1).isSameAs(memberService2);
    }
}
