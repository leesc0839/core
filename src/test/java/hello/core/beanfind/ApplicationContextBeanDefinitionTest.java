package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ApplicationContextBeanDefinitionTest {
   /* GenericXmlApplicationContext genericXmlApplicationContext = new GenericXmlApplicationContext("appconfig.xml");*/
    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

/*
    @Test
    @DisplayName("xml로 beanDefinition 조회")
    void findBeanDefinitionByXml(){
        String[] beanDefinitionNames = genericXmlApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = genericXmlApplicationContext.getBeanDefinition(beanDefinitionName);
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinition = " + beanDefinition);
            }
        }
    }
*/

    @Test
    @DisplayName("AppConfig.class로 beanDefinition 조회")
    void findBeanDefinitionByClass(){
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = annotationConfigApplicationContext.getBeanDefinition(beanDefinitionName);
            System.out.println("beanDefinition = " + beanDefinition);
        }
    }

}
