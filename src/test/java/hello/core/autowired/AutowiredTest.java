package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutoWiredOpen() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
/*        Assertions.assertThrows(UnsatisfiedDependencyException.class, () -> {
            AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
        });*/
    }

    static class TestBean {

/*        // UnsatisfiedDependencyException 발생
        @Autowired
        public void test1(Member notBean1) {
            System.out.println("notBean1 = " + notBean1);
        }*/

        @Autowired(required = false) // bean이 없으면, 호출 자체가 안된다.
        public void test2(Member notBean2) {
            System.out.println("notBean2 = " + notBean2);
        }

        @Autowired
        public void test3(@Nullable Member notBean3){ // null로 채워져서 호출된다.
            System.out.println("notBean3 = " + notBean3);
        }

        @Autowired
        public void test4(Optional<Member> notBean4){ // Optional.empty가 채워져서 호출된다.
            System.out.println("notBean4 = " + notBean4);
        }

    }

}
