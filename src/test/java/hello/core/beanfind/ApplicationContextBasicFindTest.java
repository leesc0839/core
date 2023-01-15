package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberRepository memberRepository = new MemoryMemberRepository();
    @Test
    @DisplayName("이름으로 조회하기")
    void findBeanbyName() {
        //when
        MemberService bean = ac.getBean("memberService", MemberService.class);
        System.out.println("bean.getClass() = " + bean.getClass());

        //then
        Assertions.assertThat(bean).isInstanceOf(MemberServiceImpl.class);
    }


    @Test
    @DisplayName("클래스로 조회하기")
    void findBeanbyClass() {
        //when
        MemberService bean = ac.getBean(MemberService.class);
        //then
        Assertions.assertThat(bean).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("조회가 불가능")
    void findBeanAndError() {
        assertThrows(NoSuchBeanDefinitionException.class, ()-> ac.getBean("xxx", MemberService.class));
    }

}