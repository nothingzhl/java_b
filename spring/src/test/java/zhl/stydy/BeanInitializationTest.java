package zhl.stydy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Lazy;
import zhl.study.factory.DefaultUserFactory;
import zhl.study.factory.IUserFactory;

@Configurable
public class BeanInitializationTest {

    private AnnotationConfigApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationTest.class);
        applicationContext.refresh();
    }

    @Test
    void testPostConstruct() {
        IUserFactory bean = applicationContext.getBean(IUserFactory.class);
        System.out.println(bean);
        System.out.println(bean.creatUser());
    }

    @AfterEach
    void tearDown() {
        applicationContext.close();
    }

    @Bean(initMethod = "initMethod",destroyMethod = "doDestroy")
    @Lazy
    public DefaultUserFactory getUserFactory(){
        return new DefaultUserFactory();
    }


}
