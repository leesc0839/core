package hello.core.beanfind;

import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextDuplicateFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(DuplicateAppConfig.class);

    @Test
    @DisplayName("중복된 타입 존재할 때 타입으로 조회시 예외 발생?")
    void findBeanByNameDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("중복된 타입 존재할 때 key로 조회")
    void findBeanByKey() {
        //given
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
        //when
        //then
    }

    @Test
    @DisplayName("중복된 타입 모두 조회 할 때 ")
    void findAllBeanByType() {
        //given
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);

        //when

        for (String key : beansOfType.keySet()) {
            System.out.println(key + " " + ac.getBean(key));
        }
        //then

        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class DuplicateAppConfig {

        @Bean
        MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
