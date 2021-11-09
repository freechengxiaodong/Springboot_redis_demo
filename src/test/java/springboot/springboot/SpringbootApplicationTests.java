package springboot.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import springboot.springboot.bean.Person;

@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    Person person;
    //IOC 容器
    @Autowired
    ApplicationContext ioc;

    @Test
    public void testHelloService() {
        //校验 IOC 容器中是否包含组件 personService
        boolean b = ioc.containsBean("personService");
        if (b) {
            System.out.println("personService 已经添加到 IOC 容器中");
        } else {
            System.out.println("personService 没添加到 IOC 容器中");
        }
    }

    @Test
    void contextLoads() {
        System.out.println(person);
    }


}
