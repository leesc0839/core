package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 클래스로 getBean을 하면, exception 발생")
    void findBeanByParentClass() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("특정 이름으로 getBean 하기")
    void findBeanByName() {
        DiscountPolicy fixDisCountPolicy = ac.getBean("fixDisCountPolicy", DiscountPolicy.class);
        assertThat(fixDisCountPolicy).isInstanceOf(FixDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 클래스를 기준으로 하위 클래스들을 가져오기")
    void findBeansByParentClass() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            DiscountPolicy discountPolicy = beansOfType.get(key);
            System.out.println("key = " + key + " " + "discountPolicy = " + discountPolicy);
        }
    }
    
    @Test
    @DisplayName("object 클래스를 기준으로 모든 bean들을 가져오기")
    void findAllBeansByObjectClass(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            Object bean = beansOfType.get(key);
            System.out.println("bean = " + bean);
        }
    }

    @Configuration
    static class TestConfig {
        @Bean
        DiscountPolicy fixDisCountPolicy() {
            return new FixDiscountPolicy();
        }

        @Bean
        DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }
    }
}
