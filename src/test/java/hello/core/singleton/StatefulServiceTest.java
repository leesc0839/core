package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(StatefulAppConfig.class);

    @Test
    @DisplayName("싱글톤으로 구성할 때, 특정 클라이언트가 필드를 변경할 수 있으면 안된다.")
    void test() {
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        int price1 = statefulService1.order("thread1", 10000);
        int price2 = statefulService2.order("thread2", 20000);

        // Assertions.assertThat(statefulService1.getPrice()).isEqualTo(10000); 에러 발생
        Assertions.assertThat(price1).isEqualTo(10000);

    }

    @Configuration
    static class StatefulAppConfig {
        @Bean
        StatefulService statefulService() {
            return new StatefulService();
        }
    }
}